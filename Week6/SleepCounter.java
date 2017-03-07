import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SleepCounter {
	public static boolean killer = true;
	public static void main(String args[]) throws IOException{
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		Sleepy sp = new Sleepy(); 
		sp.start();
		if (stdIn.readLine().equals("0")){
			killer = false; 
		}
		System.out.println(sp.counter);
	}
}

class Sleepy extends Thread {
	int counter =0; // to increase 
	public Sleepy(){
		
	}
	public void run() {
		while (SleepCounter.killer){
				try { 
					counter++ ; 
					Thread.sleep(1000); //sleep for one second
				}
				catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("user input detected!"); // print 5 
				}
			
		}
		}
	
}
