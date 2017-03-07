import java.util.concurrent.atomic.AtomicInteger;

public class LockStaticVariables {
	public static int saving = 5000;
	public static int cash = 0;
	
	public static void main(String args[]){   	
		int numberofThreads = 10000;
		WD[] threads = new WD[numberofThreads];
		AtomicInteger total = new AtomicInteger(5000); //saving + cash = 5000 
		
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new WD(total);
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is: " + LockStaticVariables.cash);
	}
}

class WD extends Thread {	
	private AtomicInteger total; 
	public WD(AtomicInteger total){
		this.total = total; //object passed on to every single thread from the main args 
	}
	public void run () {
		synchronized(total){ //try not to change the lock because if it does, it can make the object to become something else
			if (LockStaticVariables.saving >= 1000) {
				System.out.println("I am doing something.");			
				LockStaticVariables.saving = LockStaticVariables.saving - 1000;
				LockStaticVariables.cash = LockStaticVariables.cash + 1000;
				
			}		
		}
	}	
}

