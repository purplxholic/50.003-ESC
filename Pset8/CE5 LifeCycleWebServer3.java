package Week10;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sun.misc.Queue;
 
public class LifeCycleWebServer3 {
//	private static final ExecutorService exec = new ScheduledThreadPoolExecutor (100);
	static int  corePoolSize  =  100;
	static int  maxPoolSize   =  100;
	static long keepAliveTime = 5000;
	private static final ExecutorService exec = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 
			keepAliveTime,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321);
		int noOfClient = 0; 
		//modify here 
		//if receive special message, server shut down NOW 
		while (true) { 
			try {
				final Socket connection = socket.accept();
				noOfClient ++; 	
				
				Runnable task = new Runnable () {
					public void run() {
							handleRequest(connection);
							
					}
				};
	
				exec.execute(task);
				System.out.println("No of Clients: " + noOfClient);
			} catch (RejectedExecutionException e) { 
				if (!exec.isShutdown()) {
					System.out.println("LOG: task submission is rejected.");
				}
				e.printStackTrace();
				break;
			}			
		}
		 
    }
    
    public static void stop() {
    	exec.shutdownNow(); //stop all existing running 
    	//need to handle thread interrupt 
    }

	protected static void handleRequest(Socket connection) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			String fromClient = in.readLine(); 
			System.out.println("Recieved:" +fromClient);

				fromClient = in.readLine();
				BigInteger number = new BigInteger(fromClient);
				
				BigInteger result = factor(number);
				//System.out.println("sending results: " + String.valueOf(result));
				out.println(result);
				out.flush();
				
//			}
			in.close();
			out.close();
			connection.close();
//			stop();
		}
		catch (Exception e) {
			System.out.println("Something went wrong with the connection");
		}
	}
	
	private static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {	
			//code to handle the interrupt 
			if (Thread.interrupted()){
				break; 
			}
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false);
		return null;
	}
}