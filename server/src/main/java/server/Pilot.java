package server;

public class Pilot {

	public static void main(String[] args) {

		ServerCom sc;
		Thread t1;
		int n;

		sc = ServerCom.getCom();
		n = 0;
		while (n++ < 5) {
			if (sc.handle()) {
				t1 = new Thread(sc);
				t1.start();
			}
		}

	}

}
