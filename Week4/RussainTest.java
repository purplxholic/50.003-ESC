package Week4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class RussainTest {
	
	
	//black-box testing :  you are told what you have , you only know this multiplies the numbers together 
	@Test 
	public void black(){
		assertEquals(0,Russian.multiply(19, 0));
	}
	@Test 
	public void black2(){
		assertEquals(0,Russian.multiply(0, 0));
		
	}
	@Test 
	public void black3(){
		assertEquals(15,Russian.multiply(3, 5));
	}
	
	//white box testing with 100% branch-coverage check if condition met or not cases 
	@Test 
	//test for while (n<0)  and m is -ve
	public void white(){
		assertEquals(15,Russian.multiply(-3, -5));
	}
	
	@Test 
	//test for while (n>0) and m is -ve 
	public void white1(){
		assertEquals(-15,Russian.multiply(-3, 5));
	}
	
	@Test 
	//test for when n%2==1 does not make it at first 
	public void white2(){
		assertEquals(12,Russian.multiply(3, 4));
	}
	
	@Test 
	// when n%2==1 is satisfied immediately 
	public void white3(){
		assertEquals(6,Russian.multiply(3, 2));
	}
	
	@Test 
	//test for while (n>0) and m is +ve 
	public void white4(){
		assertEquals(15,Russian.multiply(3, 5));
	}
	
	@Test 
	//test for while (n<0)  and m is +ve
	public void white5(){
		assertEquals(-15,Russian.multiply(3, -5));
	}
	//fault testing - make sure it fails ! 1/x test case just break it! 
	@Test
	
	//double 
	public void break0(){
		assertEquals(-100.001,Russian.multiply(-10,10));
	}
	
	@Test 
	//double for inputs 
	public void break1(){
		assertSame(120,Russian.multiply(60.0, 2.0));
	}
	
	@Test 
	//big numbers -> leads to int overflow 
	public void break2(){
		assertEquals(1000000000,Russian.multiply(50000000, 50000000));
		
	}
}
