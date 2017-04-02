package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebServer {
	public static void main (String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(4321, 1000);

		long startTime = 0;
		while (true) {
			Socket connection = socket.accept();
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
			}
//			handleRequest(connection);
			
			//using threads, using the same method  
			Runnable task = new Runnable(){
				public void run(){
					try {
						handleRequest(connection);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			new Thread(task).start();
		}
	}
	
	//did seqeuential
	private synchronized static void handleRequest (Socket connection) throws Exception {
		//todo
		//call this method and read the number from the and call factor() 
		//seq request server 
		//or start a new thread ; measured performance 
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
