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
		
		ssock = null; 
		sockOut = null;
		sockIn = null;
		String tstring = "0";
		try {
			ssock = new Socket(hostName, hostPort);
			sockOut = new BufferedWriter(new OutputStreamWriter(ssock.getOutputStream()));
			sockIn = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
			sockOut.write(tstring, 0, tstring.length());
			sockOut.newLine();
			sockOut.flush();
			tstring = "mrmagic";
			sockOut.write(tstring, 0, tstring.length());
			sockOut.newLine();
			sockOut.flush();
			ssock.close();
			sockOut.close();
			sockIn.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
	}

	boolean checkLogin() {

		try {
			if (sockIn.readLine().equals(LOGIN_ERROR))
				return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;

	}

	public static NetCom getCom(String hostName, int hostPort) {

		return new NetCom(hostName, hostPort);

	}

}
