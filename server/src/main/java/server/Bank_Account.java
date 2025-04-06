package server;

import java.sql.*;

class Bank_Account {
	
	private final Connection pgServer;
	private final int accountID;
	
	Bank_Account(Connection pgServer, int accountID) {
		
		this.pgServer = pgServer;
		this.accountID = accountID;
		
	}

	int getID() {

		return accountID;

	}

}
