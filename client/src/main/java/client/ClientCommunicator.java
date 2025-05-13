package client;

import java.net.*;
import java.time.Duration;
import java.util.*;
import java.io.*;

class ClientCommunicator {
	
	private static final String LOGIN_ERROR = "ACCLOG ERR";
	private String hostName;
	private int hostPort;
	private Socket ssock;
	private BufferedWriter sockOut;
	private BufferedReader sockIn;
	
	private ClientCommunicator(String hostName, int hostPort) {
		
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

		System.out.println("Writing login values to server.");
		String sform = String.valueOf(accountNum);
		sockOut.write(sform, 0, sform.length());
		sockOut.newLine();
		sockOut.flush();
		sockOut.write(passwd, 0, passwd.length());
		sockOut.newLine();
		sockOut.flush();

	}
	
	Account_Template getAccountDetails() throws IOException {
		
		Account_Template at = new Account_Template();
		at.setFirstName(sockIn.readLine());
		at.setLastName(sockIn.readLine());
		at.setAccountID(Integer.valueOf(sockIn.readLine()));
		at.setPassword(sockIn.readLine());
		at.setBalance(Double.valueOf(sockIn.readLine()));
		return at;
		
		
	}

	boolean checkLogin() throws IOException {

		String s;
		if ((s = sockIn.readLine()).equals(LOGIN_ERROR))
				return false;
		System.out.println(s);
		return true;

	}
	
	void closeCom() throws IOException {
	
		sockOut.close();
		sockIn.close();
		ssock.close();
	
	}
	

	public static ClientCommunicator getCom(String hostName, int hostPort) {

		return new ClientCommunicator(hostName, hostPort);

	}

}
