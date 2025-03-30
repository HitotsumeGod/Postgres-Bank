package client;

@SuppressWarnings("unused")
class Account_Template {

	private String firstName = null;
	private String lastName = null;
	private Integer accountID = null;
	private String passwd = null;
	private Double balance = null;
	private Integer[] linkedAccounts = null;
	private Float interestRate = null;

	private Account_Template() {}
	
	String getFirstName() {
		
		return this.firstName;
		
	}
	
	String getLastName() {
		
		return this.lastName;
		
	}
	
	Integer getAccountID() {
		
		return this.accountID;
		
	}
	
	String getPassword() {
		
		return this.passwd;
		
	}
	
	Double getBalance() {
		
		return this.balance;
		
	}
	
	Integer[] getLinkedAccounts() {
		
		return this.linkedAccounts;
		
	}
	
	Float getInterestRate() {
		
		return this.interestRate;
		
	}
			

	static Account_Template setFirstName(Account_Template t, String firstName) {

		t.firstName = firstName;
		return t;

	}

	static Account_Template setLastName(Account_Template t, String lastName) {

		t.lastName = lastName;
		return t;

	}

	static Account_Template setAccountID(Account_Template t, Integer accountID) {

		t.accountID = accountID;
		return t;

	}

	static Account_Template setPassword(Account_Template t, String passwd) {

		t.passwd = passwd;
		return t;

	}

	static Account_Template setBalance(Account_Template t, Double balance) {

		t.balance = balance;
		return t;

	}

	static Account_Template setLinkedAccounts(Account_Template t, Integer[] linkedAccounts) {

		t.linkedAccounts = linkedAccounts;
		return t;

	}

	static Account_Template setInterestRate(Account_Template t, Float interestRate) {

		t.interestRate = interestRate;
		return t;

	}

	static Account_Template build() {

		return new Account_Template();

	}

}
