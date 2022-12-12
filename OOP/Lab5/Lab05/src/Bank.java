import java.util.ArrayList;
public class Bank {
	//1.variable
		private ArrayList<BankAccount> accounts;
		//2.constructor
		public Bank(){
			accounts = new ArrayList<BankAccount>();
		}
		//3.methods
		//add an account to this bank
		public void addAccount(BankAccount a){
			accounts.add(a);
		}
		//gets the sum of the balances of all accounts in this bank
		public double getTotalBalance(){
			double totalbalance = 0;
			for(int i=0;i<accounts.size();i++) {
				totalbalance += accounts.get(i).getBalance();
			}
			return totalbalance;
		}
		//counts the number of bank account whose balance is at least given value.
		public int countBalanceAtLeast(double atLeast){
			int count = 0;
			for(int i=0;i<accounts.size();i++) {
				if(accounts.get(i).getBalance() >= 10000)
					count++;		
			}
	        return count;
		}
		//finds a bank account with a given number
		public BankAccount find(int accountNumber){
			for(int i=0;i<accounts.size();i++) {
				if(accounts.get(i).getAccountNumber() == accountNumber)
					return accounts.get(i);		
			}
			return null;
		}
		//gets the bank account which has minimum balance.
	    public BankAccount getMin(){
	    	int min=0;
	    	for(int i=0;i<accounts.size();i++) {
				if(accounts.get(i).getBalance() < accounts.get(min).getBalance())
					min = i;		
			}
	    	if(accounts.size() != 0)
	    		return accounts.get(min);
	    	return null;
	    }	

}
