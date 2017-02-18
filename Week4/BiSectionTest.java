package Week4;


import org.junit.Before;
import org.junit.Test;

public class BiSectionTest {
	private BiSectionExample bi;
	
	@Before 
	public void runBeforeEachTest()
	{
		bi = new BiSectionExample();
	}
	
	@Test
	public void test4MethodCoverage () {
		//System.out.print(bi.root(0.5, 100.3, 0.1));
		assert (bi.root(0.5, 100.3, 0.1) >= 100);
		//question: should we assert the returned value is the exact value we expect?
		//better the assertion, better the result is 
		/*
		 * you put larger than 100 to take account to random rounding errors eg
		 * in python 0.2+0.3 = 0.300000000001 so that's why here you do >= 100 
		 * 
		 * if you see printing it out 'once' means the loop has been done at least once s
		 */
	}
	
	@Test 
	public void test4LoopCoverage1 () {//loop once
		assert(bi.root(0,100,80) > 50);
	}
}
