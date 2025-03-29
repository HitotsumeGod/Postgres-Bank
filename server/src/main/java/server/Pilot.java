package server;

import java.io.IOException;

public class Pilot {

	public static void main(String[] args) {

		ServerCom sc;
		Thread t1;
		int n;

		try {
			sc = ServerCom.getCom();
			sc.getCom();
			n = 0;
			while (n++ < 5) {
				if (sc.handle()) {
					t1 = new Thread(sc);
					t1.start();
				}
			}
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

}
