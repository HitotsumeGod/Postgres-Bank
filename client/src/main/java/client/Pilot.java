package client;

public class Pilot {
	
	private static final String HOST_IP = "127.0.0.1";
	private static final int HOST_PORT = 6666;

	public static void main(String[] args) {

		ClientCommunicator cc = ClientCommunicator.getCom(HOST_IP, HOST_PORT);
		Screen sc = Screen.createLoginScreen(cc);
		sc.updateScreen();

	}

}
