package client;

public class Pilot {

	public static void main(String[] args) {

		Screen sc = Screen.getScreen();
		NetCom nc = NetCom.getCom("127.0.0.1", 4445);
		sc.updateScreen();
		nc.getConnection();

	}

}
