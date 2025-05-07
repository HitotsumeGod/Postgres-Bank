package server;

class BankProfile {

	private final Bank_DB bank;
	private final Bank_Account acc;

	BankProfile(Bank_DB bank, Bank_Account acc) {

		this.bank =  bank;
		this.acc = acc;

	}

	Bank_DB getBank() { return this.bank; }
	Bank_Account getAccount() { return this.acc; }

}