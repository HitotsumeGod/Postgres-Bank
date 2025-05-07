package server;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Pilot {

	public static void main(String[] args) {

		ServerCommunicator sc;
		ExecutorService ex;
		int n;

		try {
			sc = ServerCommunicator.getCom();
			ex = Executors.newCachedThreadPool();
			n = 0;
			while (n++ < 5) {
				if (sc.handle()) {
					ex.execute(sc);
				}
			}
			ex.shutdown();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

}
