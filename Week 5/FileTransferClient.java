package Week5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class FileTransferClient2 {
	public static void main(String args[]) throws Exception{
		String hostName = "localhost";
	    //String hostIP = "10.11.3.28";
	    //String hostName = "fe80::7517:c1af:b2bb:da73%4";
	    int portNumber = 4321;
	
	//    Socket echoSocket = new Socket(hostName, portNumber);
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber);
		echoSocket.setSoTimeout(1000); //10 second
		echoSocket.connect(sockaddr, 100);
	    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
	    BufferedReader in =
	            new BufferedReader(
	                new InputStreamReader(echoSocket.getInputStream()));
	    BufferedReader stdIn =
	            new BufferedReader(
	                new InputStreamReader(System.in));
	    ///Stuff for file part; code from earlier part  
	    File file = new File("C:\\Users\\The Gt Zan\\Documents\\50.003 Software\\forqn4.txt"); //file to test 
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr); 
	    String userInput ;
	    String sendInput;
	    String fromserver; 
	    boolean sending = true; 
	    
	    while ((userInput = br.readLine())!= null){
	    	while(true){
		    		out.println(userInput);
		    		out.flush(); 
		    		System.out.println("sending..");
		    	try{
		    		fromserver = in.readLine(); //read any input from the server 
		    		if (fromserver.equals("ok")){ // from server side 
		    			break; 
		    		}
		    	}
		    	catch(SocketTimeoutException s){ //resend the stuff 
		    		System.out.println("Timeout!");
		    		
		    		out.println(userInput);
		    		out.flush(); 
		    		
		    	}
	    	}
	    }
	    System.out.println("done sending...");
	    //breaks when there is no more 
	    out.println("done"); 
	    out.flush();

	    
	    
	    br.close();
	    fr.close(); 
	    echoSocket.close();
	    in.close();
	    out.close();
	    stdIn.close();    
	    
	}

}
