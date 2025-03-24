package server;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

class Bank_DB {
	
	private Connection corecon = null;
	private final String psqlServer = "jdbc:postgresql://127.0.0.1:5432/testdb";
	private final String psqlUser = "postgres";
	private final String psqlPass = "muser";
	
	Bank_DB() throws SQLException {
	
		Properties opts = new Properties();	
		opts.setProperty("user", psqlUser);
		opts.setProperty("password", psqlPass);
		corecon = DriverManager.getConnection(psqlServer, opts);
		
	}

	ResultSet queryIt(String query) throws SQLException {

		Statement st;
		ResultSet rs;
		rs = null;
		st = corecon.createStatement();
		if ((rs = st.executeQuery(query)) == null)
			throw new NullPointerException;
		return rs;

	}

	Bank_Account login(int account_num, String password) throws SQLException {
		
		Statement st = corecon.createStatement();
		ResultSet rs = st.executeQuery("SELECT accounts.account_id, accounts.passwd FROM accounts");
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
	
	String mkTable(String tableName, HashMap<String, String> tableVals) throws SQLException {
		
		Statement st;
		String query;
		query = String.format("CREATE TABLE %s (", tableName);
		for (String key : tableVals.keySet()) {
			query += key + ' ';
			query += tableVals.get(key) + ", ";
		}
		st = corecon.createStatement();
		st.executeUpdate(query.substring(0, query.length() - 2) + ')');
		return "";
		
	}

}
