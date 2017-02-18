package Week4;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.*;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)

//parameterized test 
public class QuickSortTest {
	int[] sorted;
	int[] tosort;
	
	
	public QuickSortTest(int[] sorted, int[] tosort){
		//input and output are going to be parameters
		this.sorted = sorted;
		this.tosort = tosort;
	}
	
	@Parameters
	//params.add must follow the constructor of quicksorttest class 
	public static Collection<Object[]> parameters(){
		//input needs to be input[] for quicksort.sort()
		Collection<Object[]> params = new ArrayList<>();
		params.add(new Object[] { iA(1,2,3,4,5) , iA(5,4,3,2,1) }); //test 1 
		params.add(new Object[] { iA(1,2,3,4,5,6) , iA(5,4,7,6,3,2,1) }); //test 2
		
		return params;
		
	}
	
	private static int[] iA(int... vals){ //int... accepts any number of variables will be automatically created into an array
		return vals;
	}
	
	@Test
	public void quickSortTest(){
		(new QuickSort()).sort(tosort); //static problem solved by creation of new objects 
		for (int i=0; i<sorted.length; ++i){
			assertEquals(sorted[i], tosort[i]); //to compare each array in params
		}
		assertEquals(sorted.length, tosort.length); //to make sure that the length of int[] are the same otherwise fails, eg test 2
	}
}
