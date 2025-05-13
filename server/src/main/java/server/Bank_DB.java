package server;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

class Bank_DB {
	
	private static final String psqlServer = "jdbc:postgresql://127.0.0.1/bank";
	private static final String psqlUser = "postgres";
	private static final String psqlPass = "muser";
	private Connection corecon = null;
	
	Bank_DB() throws SQLException {
	
		Properties opts = new Properties();	
		opts.setProperty("user", psqlUser);
		opts.setProperty("password", psqlPass);
		corecon = DriverManager.getConnection(psqlServer, opts);
		
	}

	void makeDeposit(double amount, int id) throws SQLException {

		Statement st = null;
		ResultSet rs = null;

		rs = st.executeQuery(String.format("SELECT balance::numeric FROM accounts WHERE account_number = %d", id));
		st.executeUpdate(String.format("UPDATE accounts SET balance = %f WHERE account_number = %d", rs.getDouble(5) + amount, id));
		st.close();
		rs.close();

	}

	void makeWithdrawal(double amount, int id) throws SQLException {

		Statement st = null;
		ResultSet rs = null;

		rs = st.executeQuery(String.format("SELECT balance::numeric FROM accounts WHERE account_number = %d", id));
		st.executeUpdate(String.format("UPDATE accounts SET balance = %f WHERE account_number = %d", rs.getDouble(5) - amount, id));
		st.close();
		rs.close();

	}

	ResultSet queryIt(String query) throws SQLException {

		Statement st;
		ResultSet rs;
		rs = null;
		st = corecon.createStatement();
		System.out.println(query);
		if ((rs = st.executeQuery(query)) == null)
			throw new NullPointerException();
		return rs;

	}

	Bank_Account login(int account_num, String password) throws SQLException {
		
		Statement st = corecon.createStatement();
		ResultSet rs = st.executeQuery("SELECT accounts.account_number, accounts.passwd FROM accounts");
		while (rs.next()) {
			if (rs.getInt(1) == account_num && rs.getString(2).equals(password)) {
				rs.close();
				st.close();
				return new Bank_Account(corecon, account_num);
			}
		}
		rs.close();
		st.close();
		corecon.close();
		return null;
		
	}
	
	boolean login(String firstname, String lastname, String password) throws SQLException {
		
		Statement st = corecon.createStatement();
		ResultSet rs = st.executeQuery("SELECT accounts.first_name, accounts.last_name, accounts.passwd FROM accounts");
		while (rs.next()) {
			if (rs.getString(1).equals(firstname) && rs.getString(2).equals(lastname) && rs.getString(3).equals(password)) {
				rs.close();
				st.close();
				corecon.close();
				return true;
			}
		}
		rs.close();
		st.close();
		corecon.close();
		return false;
	}

}
