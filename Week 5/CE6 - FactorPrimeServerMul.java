
package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class FactorPrimeServerMul {
    public static void main(String[] args) throws Exception {
    	
        	int count = 0; 
        	ArrayList<Socket> sockets = new ArrayList<>();
        	ServerSocket serverSocket = new ServerSocket(4321);
        	serverSocket.setSoTimeout(10000); //10 seconds
        	
        	boolean waiting = true; 
        	
        	 while (waiting){
        		 try{
        		System.out.println("(... expecting connection ...)");
        		
        		Socket clientSocket = serverSocket.accept(); 
        		sockets.add(clientSocket);
        		System.out.println("(... connection established ...)");
        		System.out.println(serverSocket.getSoTimeout());
        		count ++; 
        		 }
        		 catch (SocketTimeoutException s){
        			 s.printStackTrace();
        			 waiting = false; 
        		 }
        	 }
        	
	        
	        	
	        	ArrayList<PrintWriter> printers = new ArrayList<>();
	        	ArrayList<BufferedReader> readers = new ArrayList<>();
	        	
	        	for (int i=0; i < sockets.size();i++){
	        		Socket clientSocket = sockets.get(i); 
	        		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
	        		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        		printers.add(out);
	        		readers.add(in); 
	        	}
	        	
	        	
	        	for (int i=0; i < sockets.size();i++){
	        		
	        		PrintWriter out = printers.get(i);
	        		
	        		String startNumber = String.valueOf(2*(i+1) + 1);
	        		String stepNumber = String.valueOf(2*sockets.size()); 
	        		out.println("4294967297 " +startNumber + " " + stepNumber);
	        		
	        		
	        	}
	        	
	        	boolean checking = true; 
	        	while (checking){
		        	for (int i=0; i < sockets.size();i++){
		        		BufferedReader in = readers.get(i); 
		        		String startNumber = String.valueOf(2*(i+1) + 1);
		        		String stepNumber = String.valueOf(2*sockets.size()); 
		        		if (in.ready()){
		        			System.out.println("Factory " + i + " received " + "4294967297 " +startNumber + " " + stepNumber + " answer is :" + (in.readLine())) ; 
		        			checking = false;
		        			break;
		        		}
		        	}
	        		
	        	}
	        	
	        	for (int i=0; i < sockets.size();i++){
	        		Socket clientSocket = sockets.get(i); 
	        		PrintWriter out = printers.get(i);
	        		out.println("Bye.");
	        		clientSocket.close();
	        		
	        	}
        	
        	}
        
    
       
    }

