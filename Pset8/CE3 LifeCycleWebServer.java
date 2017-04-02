package Week10;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sun.misc.Queue;
 
public class LifeCycleWebServer {
	private static final ExecutorService exec = new ScheduledThreadPoolExecutor (100);
	static int  corePoolSize  =    5;
	static int  maxPoolSize   =   10;
	static long keepAliveTime = 5000;
	private static final ExecutorService exec2 = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321);
		Queue bq = new Queue(); 
		//modify here 
		//if receive special message, server shut down NOW 
		while (!exec.isShutdown()) { //part A of CE5 here , change to while true let it go and maybe have something else to detect  
			try {
				final Socket connection = socket.accept();
				Runnable task = new Runnable () {
					public void run() {
							handleRequest(connection);
							
					}
				};
	
				exec.execute(task);
			} catch (RejectedExecutionException e) { 
				if (!exec.isShutdown()) {
					System.out.println("LOG: task submission is rejected.");
				}
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
			System.out.println(fromClient);
			if (fromClient.equals("4294967297")){ //for CE3 
				System.out.println("Shutting down"); 
				stop();
				

			}
			else{
				fromClient = in.readLine();
				BigInteger number = new BigInteger(fromClient);
				
				BigInteger result = factor(number);
				//System.out.println("sending results: " + String.valueOf(result));
				out.println(result);
				out.flush();
				
			}
			in.close();
			out.close();
			connection.close();
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