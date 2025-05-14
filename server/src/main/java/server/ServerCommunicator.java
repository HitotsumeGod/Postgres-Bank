package server;

import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileReader;	
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.peter.utils.Logger;

class ServerCommunicator implements Runnable {

	private static final String LOGIN_ERROR = "ACCLOG ERR";
	private static final String LOGIN_SUCCESS = "0";
	private static final String LOGFILE_PATH = "src/resources/serv.log";
	private Logger log;
	private ServerSocket ssock = null;
	private Socket csock = null;
	private BufferedReader sockIn;
	private BufferedWriter sockOut;

	private ServerCommunicator() throws IOException { 

		this.ssock = new ServerSocket(6666); 
		this.log = new Logger(new File(LOGFILE_PATH));

	}

	private BankProfile attemptLogin() {

		Bank_DB myBank = null;
		Bank_Account myAcc = null;
		ResultSet rs;
		String bankPass, queryForm;
		int bankUser, c;
	
		try {
			myBank = new Bank_DB();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			sockIn = new BufferedReader(new InputStreamReader(csock.getInputStream()));
			sockOut = new BufferedWriter(new OutputStreamWriter(csock.getOutputStream()));
			do {
				bankUser = Integer.valueOf(sockIn.readLine());
				bankPass = sockIn.readLine();
				if ((myAcc = myBank.login(bankUser, bankPass)) == null) {
					System.out.println(bankUser);
					System.out.println(bankPass);
					sockOut.write(LOGIN_ERROR, 0, LOGIN_ERROR.length());
					sockOut.flush();
				}
			} while (myAcc == null);
			sockOut.write(LOGIN_SUCCESS, 0, LOGIN_SUCCESS.length()); 	
			sockOut.newLine();
			sockOut.flush();
			queryForm = "SELECT accounts.first_name, accounts.last_name, accounts.account_number, accounts.passwd, accounts.balance::numeric FROM accounts WHERE accounts.account_number=" + myAcc.getID(); 
			rs = myBank.queryIt(queryForm);
			while (rs.next()) {
				for (int i = 1; i <= 5; i++) {
					sockOut.write(rs.getString(i), 0, rs.getString(i).length());
					sockOut.newLine();
				}
				sockOut.flush();
			}
			sockIn.close();
			sockOut.close();
		} catch (IOException | SQLException io) {
			io.printStackTrace();
		}
		return new BankProfile(myBank, myAcc);

	}

	private boolean handleClientRequest(BankProfile bp) throws SQLException {

		String am;
		
		try {
			switch((am = sockIn.readLine())) {
			case "DEPOSIT":
				bp.getBank().makeDeposit(Double.valueOf((am = sockIn.readLine())), Integer.valueOf(sockIn.readLine()));
				sockOut.write(am, 0, am.length());												//WRITE THE DEPOSITED AMOUNT BACK TO THE CLIENT TO ENSURE SANITY
				sockOut.newLine();
				sockOut.flush();
				break;
			case "WITHDRAW":
				bp.getBank().makeWithdrawal(Double.valueOf((am = sockIn.readLine())), Integer.valueOf(sockIn.readLine()));
				sockOut.write(am, 0, am.length());												//DITTO
				sockOut.newLine();
				sockOut.flush();
				break;
			case "ACCOUNT_DETAILS":
				break;
			case "LOGOUT":
				break;
			default:
				System.out.println(am);
				return false;
			}
		} catch (IOException io) {
			return false;
		}
		return true;
	}

	public void run() {
		
		BankProfile currentProfile;

		try {
			log.write("Client handler up-and-running.");
			currentProfile = attemptLogin();
			log.write(String.format("Client %d successfully logged in to account.", currentProfile.getAccount().getID()));
			while (handleClientRequest(currentProfile));
			log.write(String.format("Client %d has closed the connection.", currentProfile.getAccount().getID()));
			sockIn.close();
			sockOut.close();
			csock.close();
		} catch (IOException | SQLException io) {
			io.printStackTrace();
		}

	}

	boolean handle() {

		try {
			csock = ssock.accept();
			if (csock == null)	
				return false;
			log.write("Server has received a client connection.");
		} catch (IOException io) {
			io.printStackTrace();
		}
		return true;

	}

	static ServerCommunicator getCom() throws IOException {
	
		return new ServerCommunicator();

	}

}
