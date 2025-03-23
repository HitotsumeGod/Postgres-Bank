package server;

import java.sql.SQLException;

public class Pilot {

	public static void main(String[] args) {
		
		Bank_DB mybank;
		Bank_Account myAccount;
		try {
			mybank = new Bank_DB();
			if ((myAccount = mybank.login(1, "mrmagic")) != null)
				System.out.println(myAccount.mkDeposit(20000));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
