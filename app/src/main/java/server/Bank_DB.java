package server;

import java.sql.*;

class Bank_DB {
	
	private final Connection corecon;
	private final String psqlServer = "jdbc:postgresql://192.168.0.232:5432/bank";
	private final String psqlUser = "postgres";
	private final String psqlPass = "muser";
	
	Bank_DB() throws SQLException {
		
		corecon = DriverManager.getConnection(psqlServer, psqlUser, psqlPass);
		
	}
	
	Bank_Account login(int account_num, String password) throws SQLException {
		
		Statement st = corecon.createStatement();
		ResultSet rs = st.executeQuery("SELECT accounts.id_num, accounts.passwd FROM accounts");
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
	
	void queryAndPrint() throws SQLException {
		
		int n = 1;
		Statement st = corecon.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM accounts");
		while (rs.next()) {
			while (n < 10)
				System.out.print(rs.getString(n++) + "\t");
		    System.out.println();
		    n = 1;
		}
		rs.close();
		st.close();
		corecon.close();
		
	}

}
