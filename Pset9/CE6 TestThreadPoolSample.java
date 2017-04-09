package Week11;

import java.util.concurrent.*;

import org.junit.Test;

import junit.framework.TestCase;

public class TestThreadPoolSample extends TestCase {
	@Test 
    public void testPoolExpansion() throws InterruptedException {
        int max_pool_size = 10;
        ExecutorService exec = Executors.newFixedThreadPool(max_pool_size);
        int numThreads =0;
       
        //todo: insert your code here to complete the test case
        //hint: you can use the following code to get the number of active threads in a thread pool
        /*int numThreads = 0;
        if (exec instanceof ThreadPoolExecutor) {
        	numThreads = ((ThreadPoolExecutor) exec).getActiveCount();
        }*/
        int tasks = 50; 
        for (int i=0;i<tasks;i++){
        	exec.execute(new Runnable(){
        		public void run(){
        			
        		}
        	});
        }
        if (exec instanceof ThreadPoolExecutor) {
        	numThreads = ((ThreadPoolExecutor) exec).getActiveCount();
        }
        assertTrue(numThreads<=max_pool_size);
        exec.shutdownNow();
    }
}
