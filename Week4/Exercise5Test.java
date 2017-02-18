package Week4;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Exercise5Test {
	
	//black box don't see 
	int answer;
	int result;
	@Test
	public void TestZero(){
		answer = 0; 
		result = Exercise5.check(answer);
		assertEquals(0,result);
	}
	@Test
	public void TestPositive(){
		answer = 1; 
		result = Exercise5.check(answer);
		assertEquals(1,result);
		
	}
	@Test
	public void TestNegative(){
		answer = -1; 
		result = Exercise5.check(answer);
		assertEquals(-1,result);
	}
	
	@Test
	public void TestWRONGLY(){
		answer = 9413;
		result = Exercise5.check(answer);
		assertEquals(1,result);
		
	}
	
	//white box u know code. if code works for specific code funny thing 
	//you try to coverage 
	// don't really need whitebox 
//	@Test 
//	public void testCake(){
//		answer = 9413; 
//		result = Exercise5.check(answer);
//		assertEquals(13,answer);
//	}
//	
//	@Test
//	public void canHandleDouble(){
//		double answer = 20.0;
//		result = Exercise5.check(answer);
//		assertEquals(1,answer);
//	}
}
