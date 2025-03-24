package server;

import java.sql.SQLException;
import java.util.HashMap;

public class Pilot {

	public static void main(String[] args) {
		
		Bank_DB mybank;
		Bank_Account myAccount;
		HashMap<String, String> optsMap = new HashMap<>();
		optsMap.put("first_name", "varchar(20)");
		optsMap.put("last_name", "varchar(20)");
		optsMap.put("balance", "money");
		try {
			mybank = new Bank_DB();
			System.out.println(mybank.mkTable("maintable", optsMap));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
