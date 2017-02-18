package Week4;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

import static org.junit.Assert.*;

import org.jmock.Expectations;

public class FindMaxUsingSorting { 
    public static int findmax (int[] inputArr, Sorter sorter) {
    	int[] result = sorter.sort(inputArr);
		return result[result.length-1];	
		
		//tell the sorter to sort and this is to expect 
		
		/*
		 * you know there's sorter but you don't know anything
		 * findmax is already above
		 * so write the input and expected output 
		 * then if it's correct 
		 * the dude that made sorter doesn't have .sort he sucks
		 * so basically u assume it works and so you're gonna make
		 * a fake sorter 100% hardcoded
		 * fake sorter? 
		 */
    }
    @Test
    public void testMAX(){
    	
            Mockery context = new JUnit4Mockery();

            final Sorter sorter = context.mock(Sorter.class); //u don't know the methods , implemented 
            int[] testArray = {4,3,5};

            context.checking(new Expectations() {{ //but u still want to test 
            	
            	
            	int[] correctArray={3,4,5};
                oneOf(sorter).sort(testArray); //provide this 
                will(returnValue(correctArray)); //expect to get this 
                //with equal to compare arrays cos diff objs 
                   
            }});
            
            /*
             * you mock Sorter 
             * but when you do the step below because you mock you're going to tell 
             * sorter will return the correct array
             * since sorter doesn't have the right sort function 
             */
            
            int max = FindMaxUsingSorting.findmax(testArray,sorter);
            System.out.println(max);
            assertSame(5,max); //and then you assert that it's correct 
            
            context.assertIsSatisfied();
    }
}