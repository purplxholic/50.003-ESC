package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
public class Electionclient2 {
//	final static InetAddress INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    private static ArrayList<String> votes ;
    static int count=0; 
    public static void main(String[] args) throws Exception {
    	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    	InetAddress address = InetAddress.getByName("224.0.0.3");
    	MulticastSocket clientSocket = new MulticastSocket(PORT); 
    	votes = new ArrayList<>(); 
    	clientSocket.joinGroup(address);
    	byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		//data is out 
		System.out.print(">Please key in your vote now: ");   
		while (votes.size()!=5){
			if (count ==0){
				
				if (inFromUser.ready()){      
					
						String sentence = inFromUser.readLine();
						sendData = sentence.getBytes();
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, PORT);
						clientSocket.send(sendPacket); //sending out the message 
						count ++; 
					
				}
				else{
					continue; 
				}
			}
			
			//get back the results 
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // to receive the message 
			clientSocket.receive(receivePacket); //the one that they're really receiving from 
			String modifiedSentence = new String(receivePacket.getData()); //string from server
			if (!modifiedSentence.equals(null)){
				System.out.println("A VOTE HAS BEEN RECEIVED,  REGISTERED AND SAVED: " + modifiedSentence);
				votes.add(modifiedSentence); 
			}
		}
		
		//calculate voting 
	//computation of votes 
		int A = 0; 
		int B =0 ; 
		for (String s:votes){
			if (s.equals("A")||s.equals("a")){
				A++; 
			}
			else if(s.equals("B")||s.equals("b")){
				B++; 
			}
			else{
				continue; //spoilt votes 
			}
		}
		String result = "";
		if (A>=B) result = "A" ; 
		if(B>=A) result = "B"; 
		System.out.println("Winner is " + result);
		clientSocket.close();
    }
}

class platform extends Thread{
	public void run(){
		
	}
}

//String hostName = "localhost";
////String hostIP = "10.11.3.28";
////String hostName = "fe80::7517:c1af:b2bb:da73%4";
//int portNumber = 4321;
//
////Socket echoSocket = new Socket(hostName, portNumber);
//Socket echoSocket = new Socket();
//SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber);
//echoSocket.connect(sockaddr, 100);
//PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
//BufferedReader in =
//      new BufferedReader(
//          new InputStreamReader(echoSocket.getInputStream()));
//BufferedReader stdIn =
//      new BufferedReader(
//          new InputStreamReader(System.in));
//
//
//
//String fromServer;
//String userInput; 
//do{
////	System.out.println("Connected to my voting platform!");
//	
//  fromServer = in.readLine();
//  if (fromServer.equals("Bye.")) break; 
//
//  System.out.println("Admin: " + fromServer);
//
//  
//
//  userInput = stdIn.readLine();
//
//  
//  out.println(userInput);
//  out.flush();
//
//}while(!userInput.equals("Bye")); 
//
//
//
//
//echoSocket.close();
//in.close();
//out.close();
//stdIn.close(); 