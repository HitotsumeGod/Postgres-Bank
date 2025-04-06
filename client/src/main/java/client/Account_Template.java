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
			
	void setFirstName(String s) {
		
		this.firstName = s;
		
	}
	
	void setLastName(String s) {
		
		this.lastName = s;
		
	}
	
	void setAccountID(int i) {
		
		this.accountID = i;
		
	}
	
	void setPassword(String s) {
		
		this.passwd = s;
		
	}
	
	void setBalance(Double d) {
	
		this.balance = d;
	
	}
	
	void setLinkedAccounts(Integer[] arr) {
		
		this.linkedAccounts = arr;
		
	}
	
	void setInterestRate(Float f) {
		
		this.interestRate = f;
		
	}

}
