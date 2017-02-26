package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ChatRoom2 {
	public static void main(String[] args) throws Exception {
		
			ServerSocket serverSocket = new ServerSocket(4321);
			ArrayList<Socket> sockets = new ArrayList<>();
			ArrayList<PrintWriter> printers = new ArrayList<>();
        	ArrayList<BufferedReader> readers = new ArrayList<>();
			int count = 0; 
			serverSocket.setSoTimeout(10000); //10 seconds
        	
        	boolean waiting = true; 
			while (waiting){
        		 try{
        		System.out.println("(... expecting connection ...)");
        		
        		Socket clientSocket = serverSocket.accept(); 
        		sockets.add(clientSocket);
        		System.out.println("(... connection established ...)");
//        		System.out.println(serverSocket.getSoTimeout());
        		count ++; 
        		 }
        		 catch (SocketTimeoutException s){
//        			 s.printStackTrace();
        			 waiting = false; 
        		 }
        	 }
			 
			for (int i=0; i < sockets.size();i++){
				
				Socket clientSocket = sockets.get(i); 
        		
        		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        		
        		readers.add(in); 
			}
			System.out.println("CHAT START!");
			if (sockets.size()==0){
				System.out.println("(... oops, no one joined! ...)");
				serverSocket.close(); 
			}
			else{
				while (true){
					for (int j=0;j<sockets.size();j++){
						boolean window = true; 
						
						Socket client = sockets.get(j);
						BufferedReader in = readers.get(j);
						client.setSoTimeout(5000);
						while(window){
			        		try{
			        			

				        			System.out.println("Client " + j + " :"+ in.readLine());
				        					        		
			        		}
			        		catch (SocketTimeoutException s){
			        			System.out.println("Sorry, client " + j +" , your time has run out!");
//			        			 s.printStackTrace();
			        			
			        			  
			        		 }
			        		finally{

			        			window = false;
			        		}
		        		 
						}
						

					}
				}
			}
        	
			
	}

}
