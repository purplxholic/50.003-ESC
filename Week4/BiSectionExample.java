package Week4;

public class BiSectionExample {
	public double root(double d, double e, double f) throws IllegalArgumentException {
		double middle;
		/*
		 * bi section - cutting it up 
		 * so it prove that all sections have been tested, must see that 1,2,3,4 have been printed
		 */
		if (d >= e) {
			System.out.println("1");
			throw new IllegalArgumentException ("low must be lower than high");
		}

		System.out.println(d + " " + e + " " + f);
		while(e-d > f) { //9.1 - 8 = 1.5 > 1
			System.out.println("2");
			System.out.println("once");
		   middle = (e + d) / 2; // middle = 8.55 < 9.1
		   if (middle < e) {
				System.out.println("3");
			   d = middle;			   
		   }
		   else {
				System.out.println("4");
			   e = middle;
		   }
	    }
		System.out.println("answer is: " + (e + d) / 2);
	    return (e + d) / 2;
	}
}
