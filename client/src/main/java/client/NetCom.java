package client;

import java.net.*;
import java.time.Duration;
import java.util.*;
import java.io.*;

class NetCom {
	
	private static final String LOGIN_ERROR = "ACCLOG ERR";
	private String hostName;
	private int hostPort;
	private Socket ssock;
	private BufferedWriter sockOut;
	private BufferedReader sockIn;
	
	private NetCom(String hostName, int hostPort) {
		
		this.hostName = hostName;
		this.hostPort = hostPort;
		
	}
	
	void getConnection() {
		
		try {
			ssock = new Socket(hostName, hostPort);
			sockOut = new BufferedWriter(new OutputStreamWriter(ssock.getOutputStream()));
			sockIn = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
		} catch (IOException io) {
			io.printStackTrace();
		}
		
	}

	void writeLogin(int accountNum, String passwd) throws IOException {

		String sform = String.valueOf(accountNum);
		sockOut.write(sform, 0, sform.length());
		sockOut.newLine();
		sockOut.flush();
		sockOut.write(passwd, 0, passwd.length());
		sockOut.newLine();
		sockOut.flush();

	}
	
	Account_Template getAccountDetails() throws IOException {
		
		String first_name = sockIn.readLine();
		String last_name = sockIn.readLine();
		Integer account_number = Integer.parseInt(sockIn.readLine());
		String passwd = sockIn.readLine();
		Double balance = Double.parseDouble(sockIn.readLine());
		return Account_Template.setFirstName(Account_Template.setLastName(Account_Template.setAccountID(Account_Template.setPassword(Account_Template.setBalance(Account_Template.build(), balance), passwd), account_number), last_name), first_name);
		
	}

	boolean checkLogin() throws IOException {

		if (sockIn.readLine().equals(LOGIN_ERROR))
				return false;
		return true;

	}
	
	void closeCom() throws IOException {
	
		sockOut.close();
		sockIn.close();
		ssock.close();
	
	}
	

	public static NetCom getCom(String hostName, int hostPort) {

		return new NetCom(hostName, hostPort);

	}

}
