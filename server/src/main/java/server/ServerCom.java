package server;

import java.net.*;
import java.io.*;
import java.sql.*;

class ServerCom implements Runnable {

	private static final String LOGIN_ERROR = "ACCLOG ERR";
	private static final String LOGIN_SUCCESS = "0";
	private ServerSocket ssock = null;
	private Socket csock = null;
	private BufferedReader sockIn;
	private BufferedWriter sockOut;
	private Thread t;

	private ServerCom() throws IOException {

		ssock = new ServerSocket(6666);

	}

	public void run() {
		
		Bank_DB myBank = null;
		Bank_Account myAcc;
		ResultSet rs;
		String bankPass, queryForm;
		int bankUser, c;
		boolean cont;
		
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
					sockOut.write(LOGIN_ERROR, 0, LOGIN_ERROR.length());
					sockOut.flush();
				}
			} while (myAcc == null);
			sockOut.write(LOGIN_SUCCESS, 0, LOGIN_SUCCESS.length()); 	
			sockOut.newLine();
			sockOut.flush();
			queryForm = "SELECT accounts.first_name, accounts.last_name, accounts.account_number, accounts.passwd, accounts.balance::numeric, accounts.linked_accounts, accounts.interest_rate FROM accounts WHERE accounts.account_number=" + myAcc.getID(); 
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

	}

	boolean handle() throws IOException {

		csock = ssock.accept();
		if (csock == null)	
			return false;
		return true;

	}

	static ServerCom getCom() throws IOException {
	
		return new ServerCom();

	}

}
