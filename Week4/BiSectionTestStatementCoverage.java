package Week4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class BiSectionTestStatementCoverage {
	//add a few more test cases 
	
	private BiSectionExample bi;
	
	@Before 
	public void runBeforeEachTest()
	{
		bi = new BiSectionExample();
	}
	
	@Test
	public void test4MethodCoverage2 () {
		//System.out.print(bi.root(0.5, 100.3, 0.1));
		System.out.println("test 2:\n");
		assert (bi.root(100.5, 90, 1) >= 100);
		//prints 1 
		
	}
	
	@Test 
	public void test4LoopCoverage3 () {//loop once
		System.out.println("test 3:\n");
		assert(bi.root(0,100,80) > 50);
		//prints 2 & 3 
	}
	
	@Rule 
	public Timeout timeout = new Timeout(100);
	
	@Test 
	public void test4LoopCoverage4(){
		System.out.println("test4: ");
		assert(bi.root(2,3,-1)>10);
		//make it print 4 
	}
	
	@Test
	public void test4LoopCoverage5(){
		System.out.println("test5: ");
		assert(bi.root(20, 20.34454545, 150)>=0);
		//does not print anything 
	}
}
