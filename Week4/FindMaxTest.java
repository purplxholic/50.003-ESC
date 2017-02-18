package Week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindMaxTest {
	//give 1 failure, one error and one resulting in pass 
	/*
	 * assertTrue is used when your condition should be true and check 
	 * assertFalse is used when your condition should be false ! 
	 * if you want to change something, change the main method! 
	 */
	
	@Test
	public void testForPass(){
		int[] test1 = {2,2,3,4,5,5} ;
		FindMax a = new FindMax();
		int test1return = FindMax.max(test1);
		assertTrue(test1return == 5);
				
	}
	
	@Test
	public void testForError(){
		// will fail because it cannot take in a double 
		double[] test2 = {5.5,6.0,7.0,10.11};
		int test2return = FindMax.max(test2);
		assertTrue(test2return == 10.11);
	}
	
	@Test
	public void testForFailure(){
		int[] test3 = {10,3,9,2,11}; //current method does not check the last number of the last 
		int test3return = FindMax.max(test3);
		assertTrue(test3return == 11); //Failure is when your code perfectly works but your assert is wrong 
	}
	
}
