package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

public class TicTacToePlayer {
	public static void main(String args[]) throws Exception{
		String hostName = "localhost";
	    //String hostIP = "10.11.3.28";
	    //String hostName = "fe80::7517:c1af:b2bb:da73%4";
	    int portNumber = 4321;
	
	//    Socket echoSocket = new Socket(hostName, portNumber);
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber);
		echoSocket.connect(sockaddr, 100);
	    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
	    BufferedReader in =
	            new BufferedReader(
	                new InputStreamReader(echoSocket.getInputStream()));
	    BufferedReader stdIn =
	            new BufferedReader(
	                new InputStreamReader(System.in));
	    
	    String userInput ;
	    String fromserver;
	    do {
//	    	if (in.ready()){
	    		
//	    		System.out.println(fromserver);
//	    	}
            userInput = stdIn.readLine();
            fromserver = in.readLine(); 
            
//            System.out.println("Client: " + userInput); 
            out.println(userInput); //sending to server 
            out.flush();
            System.out.println("Movement sent!" ); 
            System.out.println(fromserver);
           
            
        } while (!fromserver.equals("Bye.")); 
	    
	    
	   
	    echoSocket.close();
	    in.close();
	    out.close();
	    stdIn.close();    
	    
	}
	
}

