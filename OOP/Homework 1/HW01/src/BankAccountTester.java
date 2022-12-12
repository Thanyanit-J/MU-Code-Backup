//Thanyanit Jongjitragan
//ID 6188075
public class BankAccountTester {
	public static void main(String[] args) {
		BankAccount account1 = new BankAccount();
		account1.deposit(1000);
		account1.withdraw(500);
		account1.withdraw(400);
		System.out.println(account1.getBalance());
	}
}
