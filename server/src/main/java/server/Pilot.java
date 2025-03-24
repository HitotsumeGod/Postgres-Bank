package server;

import java.sql.SQLException;
import java.util.HashMap;

public class Pilot {

	public static void main(String[] args) {
		
		Bank_DB myBank;
		Bank_Account myAccount;
		HashMap<String, String> optsMap = new HashMap<>();
		try {
			myBank = new Bank_DB();
			myAccount = myBank.login(69, "funnyelf");
			System.out.println(myAccount.mkDeposit(10000));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
