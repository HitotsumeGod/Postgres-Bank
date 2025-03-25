package client;

import java.net.*;

class NetCom {
	
	private String hostName;
	private int hostPort;
	
	private Netcom(String hostName, int hostPort) {
		
		this.hostName = hostName;
		this.hostPort = hostPort;
		
	}
	
	void getConnection() {
		
		Socket ssock = new Socket(hostName, hostPort);
		PrintWriter sockOut = new PrintWriter(ssock.getOutputStream(), true);
		BufferedReader sockIn = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
		
	}

}
