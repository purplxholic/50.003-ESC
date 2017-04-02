package Week10;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class ExecutorWebServerCE4 {
	private static final int NTHREADS = 8;//change this for CE4 
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	
    public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);

		while (true) {
			final Socket connection = socket.accept();
			System.out.println(Runtime.getRuntime().availableProcessors());
			Runnable task = new Runnable () {
				public void run() {
					try {
						handleRequest(connection);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			exec.execute(task);
		}
    }

	protected static void handleRequest(Socket connection) throws IOException {
		//...
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String num = br.readLine();
		BigInteger bi = new BigInteger(num);
		BigInteger result = factor(bi); 
		PrintWriter out =
                new PrintWriter(connection.getOutputStream(), true);   
		out.println(result);
		out.flush();
	}
	
	private static BigInteger factor(BigInteger n) {
		BigInteger i = new BigInteger("2");
		BigInteger zero = new BigInteger("0");
		
		while (i.compareTo(n) < 0) {			
			if (n.remainder(i).compareTo(zero) == 0) {
				return i;
			}
			
			i = i.add(new BigInteger("1"));
		}
		
		assert(false);
		return null;
	}
}