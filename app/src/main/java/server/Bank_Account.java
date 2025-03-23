package server;

import java.sql.*;

class Bank_Account {
	
	private final Connection pgServer;
	private final int accountID;
	
	Bank_Account(Connection pgServer, int accountID) {
		
		this.pgServer = pgServer;
		this.accountID = accountID;
		
	}
	
	String mkDeposit(double amount) throws SQLException {
		
		Statement st;
		ResultSet rs;
		String query;
		double currentBal;
		query = String.format("SELECT accounts.balance::numeric FROM accounts WHERE accounts.id_num=%d", accountID);
		st = pgServer.createStatement();
		rs = st.executeQuery(query);
		rs.next();
		currentBal = (double) rs.getDouble(1);
		query = String.format("UPDATE accounts SET balance=%f WHERE id_num=%d", currentBal + amount, accountID);
		st.executeQuery(query);
		query = String.format("SELECT accounts.balance FROM accounts WHERE accounts.id_num=%d", accountID);
		rs = st.executeQuery(query);
		rs.next();
		if (rs.getDouble(1) == currentBal + amount)
			return "Deposit succeeded!";
		return "Deposit failed.";
		
	}

}
