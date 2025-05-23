package client;

import java.net.Socket;
import java.time.Duration;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import org.peter.utils.Logger;

class ClientCommunicator {
	
	private static final String LOGIN_ERROR = "ACCLOG ERR";
	private static final String LOGFILE_PATH = "src/resources/serv.log";
	private Logger log;
	private String hostName;
	private int hostPort;
	private Socket ssock;
	private BufferedWriter sockOut;
	private BufferedReader sockIn;
	
	private ClientCommunicator(String hostName, int hostPort) {
	
		try {
			this.log = new Logger(new File(LOGFILE_PATH));
			this.hostName = hostName;
			this.hostPort = hostPort;
			this.log.write("Client established.");
		} catch (IOException io) {
			io.printStackTrace();
		}
		
	}
	
	void getConnection() {
		
		try {
			ssock = new Socket(hostName, hostPort);
			sockOut = new BufferedWriter(new OutputStreamWriter(ssock.getOutputStream()));
			sockIn = new BufferedReader(new InputStreamReader(ssock.getInputStream()));
			log.write("Client obtained connection with foreign host.");
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
		log.write("Client wrote login values to server.");

	}
	
	Account_Template getAccountDetails() throws IOException {
		
		Account_Template at = new Account_Template();
		at.setFirstName(sockIn.readLine());
		at.setLastName(sockIn.readLine());
		at.setAccountID(Integer.valueOf(sockIn.readLine()));
		at.setPassword(sockIn.readLine());
		at.setBalance(Double.valueOf(sockIn.readLine()));
		log.write("Client accepted login details from server.");
		return at;
		
		
	}

	boolean checkLogin() throws IOException {

		String s;
		if ((s = sockIn.readLine()).equals(LOGIN_ERROR)) {
			log.write("Client login attempt failed.");
			return false;
		}
		log.write("Client login attempt succeeded.");
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
