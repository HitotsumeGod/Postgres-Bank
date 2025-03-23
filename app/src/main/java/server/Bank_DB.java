package server;

import java.sql.*;

class Bank_DB {
	
	private final Connection corecon;
	private final String psqlServer = "jdbc:postgresql://192.168.0.232:5432/bank";
	
	Bank_DB() throws SQLException {
		
		corecon = DriverManager.getConnection(psqlServer, "postgres", "muser");
		
	}
	
	void queryAndPrint() throws SQLException {
		
		int n = 1;
		Statement st = corecon.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM accounts");
		while (rs.next()) 
			while (n < 5)
				System.out.print(rs.getString(n++) + ' ');
		System.out.println();
		rs.close();
		st.close();
		corecon.close();
		
	}

}
