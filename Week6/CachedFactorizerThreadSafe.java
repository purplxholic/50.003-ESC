

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FactorizerUserr {
	public static void main (String[] args) {
		Object lock = new Object(); 
		CachedFactorizerThreadSafe factorizer = new CachedFactorizerThreadSafe(lock);
		Thread tr1 = new Thread (new MyRunnablee(factorizer));
		Thread tr2 = new Thread (new MyRunnablee(factorizer));
		tr1.start();
		tr2.start();
		
		try {
			tr1.join();
			tr2.join();
		}
		catch (Exception e) {
			
		}
	}
}

class MyRunnablee implements Runnable {
	private CachedFactorizerThreadSafe factorizer;
	
	public MyRunnablee (CachedFactorizerThreadSafe factorizer) {
		this.factorizer = factorizer; 
	}
	
	public void run () {
		Random random = new Random ();
		factorizer.factor(random.nextInt(100));
	}
}

public class CachedFactorizerThreadSafe {
	//make sure any related variables are protected so like here
	//it would be lastNumber and lastFactors 
	private int lastNumber;
	private List<Integer> lastFactors;
	private long hits;
	private long cacheHits;
	private Object lock; 
	
	public CachedFactorizerThreadSafe(Object lock2) {
		// TODO Auto-generated constructor stub
		this.lock = lock2;
	}
	
	public long getHits () {
		return hits;
	}
	
	public double getCacheHitRatio () {
		return (double) cacheHits/ (double) hits;
	}
	
	public List<Integer> service (int input) {
		List<Integer> factors = null;
		synchronized(lock){
			
			++hits;
			
			if (input == lastNumber) {
				++cacheHits;
				factors = new ArrayList<Integer>(lastFactors);
			}
		}
			
			if (factors == null) {
				factors = factor(input);
				synchronized(this){
				lastNumber = input;
				lastFactors = new ArrayList<Integer>(factors);
				
			}
			
			
		}
			return factors;
	}
	
	public List<Integer> factor(int n) {		
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}
		
		return factors;
	}
}