package client;

import java.net.*;
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
		
		ssock = null; 
		sockOut = null;
		sockIn = null;
		try {
			ssock = new Socket(hostName, hostPort);
			sockOut = new BufferedWriter(new OutputStreamWriter(ssock.getOutputStream()));
			sockIn = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
		} catch (IOException io) {
			io.printStackTrace();
		}
		if (ssock == null || sockOut == null || sockIn == null)
			System.exit(1);
		
	}

	boolean checkLogin() {

		if (sockOut.readLine().equals(LOGIN_ERROR))
			return false;
		return true;

	}

	Account_Template populateGUI() {

			

	}

	public static NetCom getCom(String hostName, int hostPort) {

		return new NetCom(hostName, hostPort);

	}

}
