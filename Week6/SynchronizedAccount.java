import java.util.ArrayList;

public class SynchronizedAccount {
	int amount = 1000 ; 
	public static void main(String args[]){
		int numberofThreads = 1000;
		int amount = 1000 ;
		ArrayList<Thread> threads = new ArrayList<>(); 
		SynchronizedAccount sa = new SynchronizedAccount(amount); 
		for (int i=0; i < numberofThreads;i++){
			threads.add(new ATM2(sa)); 
		
		}
		for (int i=0; i <threads.size();i++){
			threads.get(i).start(); 
		
		}
		
	}
	
	int inBank = 0 ; 
	//SynchronizedAccount should be synchronized differently for read and write accesses 
	Object read = new Object();
	Object write = new Object(); 
	public SynchronizedAccount(int amount){
		inBank = amount; 
		System.out.println("You have started a bank account with ISTD with $" + inBank);
		
	}
	public void deposit(int amount){
		synchronized(write){
			inBank += amount ; 
			System.out.println("Successfully deposited $" + amount );
		}
		
	}
	
	public boolean withdraw(int amount){
		synchronized(write){
		if (inBank - amount < 0){
			
			System.out.println("Insufficient amount to withdraw $" + amount );
			return false; 
		}
		inBank -= amount; 
		System.out.println("Successfully withdrawn $" + amount );
		return true; 
		}
	}
	
	public void checkBalance(){
		synchronized(read){
			System.out.println("You currently have $" + inBank);
		}
	}
}

class ATM2 extends Thread {
	private final SynchronizedAccount account;
	
	public ATM2 (SynchronizedAccount account) {
		this.account = account;
	}
	
	public void run () {
		synchronized (account) {
			account.deposit(10);	
			account.checkBalance();
			account.withdraw(1000);
		}
	}
}


