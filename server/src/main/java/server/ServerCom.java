package server;

import java.net.*;
import java.io.*;
import java.sql.*;

class ServerCom implements Runnable {

	private static final String LOGIN_ERROR = "ACCLOG ERR";
	private ServerSocket ssock = null;
	private Socket csock = null;
	private BufferedReader sockIn;
	private BufferedWriter sockOut;
	private Thread t;

	private ServerCom() throws IOException {

		ssock = new ServerSocket(4445);

	}

	public void run() {
		
		Bank_DB myBank;
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
				if ((myAcc = myBank.login(bankUser, bankPass)) == null) 
					sockOut.write(LOGIN_ERROR, 0, LOGIN_ERROR.length());
				queryForm = String.format("SELECT balance FROM accounts WHERE account_id=%d", myAcc.getID());
				rs = myBank.queryIt(queryForm);
				rs.next();
				sockOut.write('D' + rs.getString(), 0, 1 + rs.getString().length());
				rs.close();
				cont = true;
				while (cont)
					switch (Integer.valueOf(sockIn.readLine())) {

					}
			} while (myAcc == null)
			sockIn.close();
			sockOut.close();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	boolean handle() {

		csock = ssock.accept();
		if (csock == null)	
			return false;
		return true;

	}

	static ServerCom getCom() throws IOException {
	
		return new ServerCom();

	}

}
