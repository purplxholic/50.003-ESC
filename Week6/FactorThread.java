import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorThread {
	public static boolean finding = false; 
	public static int identifier; 
	public static List<BigInteger> answer; 
	public static void main(String args[]) throws InterruptedException{
		int numofthreads =200; //set the number of threads you want to use 
		answer = new ArrayList<>();
		BigInteger bi = new BigInteger("15"); //4294967297
		ArrayList<Thread> threads = new ArrayList<>(); 
		for (int i=0; i<numofthreads;i++){
			threads.add(new FactorFinder(i,bi)); //create the threads 
			System.out.println("created thread " + i);
		}
		
		for (int j=0; j< threads.size();j++){
			threads.get(j).start(); 
		}
		
		while (!finding){
			System.out.println("still calculating");
			
		}
		System.out.println("Thread " + identifier + " found the answer: " + answer);
		
	}

}

class FactorFinder extends Thread{
	//constants
	int id; 
	BigInteger num;
	
	public FactorFinder(int ID, BigInteger given){
		this.id = ID; 
		num = given; //given number to factorise 
		
	}
	
	public void run(){
		prime_factorisation(num); 
		if (FactorThread.answer.size()!=0){
			FactorThread.finding = true; 
			FactorThread.identifier = this.id; 
		}
	}
	//code from earlier question 
	private static final BigInteger TWO = BigInteger.valueOf(2);

    public static void prime_factorisation(BigInteger a){
        // impossible for values lower than 2
        if(a.compareTo(TWO) < 0){
            FactorThread.answer = null;
        }

        //6 110 & 001 = 000 ;011 , add 2 111
        //since 2 is a special number
        List<BigInteger> result = new ArrayList<BigInteger>();
        while(a.and(BigInteger.ONE).equals(BigInteger.ZERO)){
            a = a.shiftRight(1);
            result.add(TWO);
        }

        //dealing with odd numbers
        // generate and divide the number using odd numbers

            BigInteger b = BigInteger.valueOf(3); //starting with 3
            while(b.compareTo(a) < 0){ //while b is less than a
                if(b.isProbablePrime(10)){ //check whether b is a prime number
                    BigInteger[] divideAndRemainder = a.divideAndRemainder(b); //returns [divided answer , mod answer]
                    if(divideAndRemainder[1].equals(BigInteger.ZERO)){ //shows that a is divisible by b
                        result.add(b); //then b is a factor
                        a = divideAndRemainder[0]; //update a
                    }
                }
                b = b.add(TWO);
            }
            result.add(b);
        FactorThread.answer = result;
//        return result;
    }
}
