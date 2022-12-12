//Thanyanit Jongjitragan
//ID 6188075
public class BankAccount {
	
	public double balance;
	
	public BankAccount() {
		balance = 0;
	}
	
	public void deposit(double deposit) {
		balance = balance + deposit;
	}
	
	public void withdraw(double withdraw) {
		balance = balance - withdraw;
	}
	
	public double getBalance() {
		return balance;
	}
}
