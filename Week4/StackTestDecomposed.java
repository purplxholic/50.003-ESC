package Week4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTestDecomposed {
	private Stack<Integer> stack;
	// setUp method using @Before syntax
	// @Before methods are run before each test
	@Before 
	public void runBeforeEachTest()
	{
		System.out.println("setting up");
		
	    stack = new Stack<Integer>();
	}

	// tear-down method using @After
	// @After methods are run after each test
	//when you run again this will run again so that you recreate a new stack 
	@After 
	public void runAfterEachTest()
	{
	    stack = null;
		System.out.println("setting down");
	}

	@Test public void testToString()
	//will it generate properly for two strings?
	{
		System.out.println("testing");
	   stack.push(new Integer(1));
	   stack.push(new Integer(2));
	   assertEquals ("{2, 1}", stack.toString());
	}
	
	
	@Test public void testRepOk()
	//this is bad - partition test cases 
	//how to intialise a new stack object without repeating the before and after 
	//need to break it down because you won't know what will cos the code to fails
	{
	  
	   check(getResult());
	}
	@Test 
	public void testRepok2(){
	   stack.push (new Integer (1));
	   
	   check(getResult());
	}
	
	@Test 
	public void testRepOk3(){
	   
	   stack.pop();
	   
	   check(getResult());
	}
	@Test
	public void testRepOk4(){
	   stack.push (new Integer (1));
	   stack.pop();
	   
	   check(getResult());
	}
	
	public void check(boolean result){
		assertEquals (true, result);
	}
	
	public boolean getResult(){
		return stack.repOK();
	}
	

}