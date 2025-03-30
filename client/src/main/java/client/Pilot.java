package client;

public class Pilot {

	public static void main(String[] args) {

		Account_Template temp = Account_Template.setFirstName(Account_Template.setLastName(Account_Template.setAccountID(Account_Template.setBalance(Account_Template.setPassword(Account_Template.build(), "mrmagic"), 200.00), 1), "naylor"), "peter");
		Screen sc = Screen.createAccountScreen(temp);
		sc.updateScreen();

	}

}
