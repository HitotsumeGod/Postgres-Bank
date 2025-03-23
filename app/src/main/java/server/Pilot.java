package server;

import java.sql.SQLException;

public class Pilot {

	public static void main(String[] args) {
		
		Bank_DB mybank;
		try {
			mybank = new Bank_DB();
			mybank.queryAndPrint();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
