package final_coursework;

public class Account extends Customer {
	private double balance;
	private int accountNumber;

	public Account(String fName, String lName, int accountNumber, double balance) {
		setFirstName(fName);
		setLastName(lName);
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public int getAccountNum() {
		return accountNumber;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		balance -= amount;
	}

}