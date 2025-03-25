package server;

import java.sql.*;

class Bank_Account {
	
	private final Connection pgServer;
	private final int accountID;
	
	Bank_Account(Connection pgServer, int accountID) {
		
		this.pgServer = pgServer;
		this.accountID = accountID;
		
	}

	double mkWithdrawal(double amount) throws SQLException {

		Statement st;
		ResultSet rs;
		String query;
		double currentBal;
		query = String.format("SELECT accounts.balance::numeric FROM accounts WHERE accounts.account_id=%d", accountID);

	}
	
	boolean mkDeposit(double amount) throws SQLException {
		
		Statement st;
		ResultSet rs;
		String query;
		double currentBal;
		query = String.format("SELECT accounts.balance::numeric FROM accounts WHERE accounts.account_id=%d", accountID);
		st = pgServer.createStatement();
		rs = st.executeQuery(query);
		rs.next();
		currentBal = (double) rs.getDouble(1);
		query = String.format("UPDATE accounts SET balance=%f WHERE account_id=%d", currentBal + amount, accountID);
		st.executeUpdate(query);
		query = String.format("SELECT accounts.balance FROM accounts WHERE accounts.account_id=%d", accountID);
		rs = st.executeQuery(query);
		rs.next();
		if (rs.getString(1) == currentBal + amount)
			return true;
		return false;
		
	}

	int getID() {

		return account_id;

	}

}
