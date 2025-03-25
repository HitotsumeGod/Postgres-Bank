class Account_Template {

	private final String firstName;
	private final String lastName;
	private final int accountID;
	private final String passwd;
	private final double balance;
	private final int[] linkedAccounts;
	private final float interestRate;

	private Account_Template() {}

	Account_Template(Account_Template filled) {

		this = filled;

	}

	static Account_Template setFirstName(Account_Template t, String firstName) {

		t.firstName = firstName;
		return t;

	}

	static Account_Template setLastName(Account_Template t, String lastName) {

		t.lastName = lastName;
		return t;

	}

	static Account_Template setAccountID(Account_Template t, int accountID) {

		t.accountID = accountID;
		return t;

	}

	static Account_Template setPassword(Account_Template t, String passwd) {

		t.passwd = passwd;
		return t;

	}

	static Account_Template setBalance(Account_Template t, double balance) {

		t.balance = balance;
		return t;

	}

	static Account_Template setLinkedAccounts(Account_Template t, int[] linkedAccounts) {

		t.linkedAccounts = linkedAccounts;
		return t;

	}

	static Account_Template setInterestRate(Account_Template t, float interestRate) {

		t.interestRate = interestRate;
		return t;

	}

	static Account_Template build() {

		return new Account_Template();

	}

}
