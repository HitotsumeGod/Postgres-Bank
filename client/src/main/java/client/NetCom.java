package client;

import java.net.*;
import java.util.*;
import java.io.*;

class NetCom {
	
	private String hostName;
	private int hostPort;
	
	private NetCom(String hostName, int hostPort) {
		
		this.hostName = hostName;
		this.hostPort = hostPort;
		
	}
	
	void getConnection() {
		
		Socket ssock = null; 
		BufferedWriter sockOut = null;
		BufferedReader sockIn = null;
		try {
			ssock = new Socket(hostName, hostPort);
			sockOut = new BufferedWriter(new OutputStreamWriter(ssock.getOutputStream()));
			sockIn = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
			String msg = "You got this?";
			sockOut.write(msg, 0, msg.length());
			sockOut.close();
			sockIn.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
	}

	public static NetCom getCom(String hostName, int hostPort) {

		return new NetCom(hostName, hostPort);

	}

}
