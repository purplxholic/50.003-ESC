package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ElectionServer {
	public static void main(String[] args) throws Exception {
		
    	int count = 0; 
    	ArrayList<Socket> sockets = new ArrayList<>();
    	ServerSocket serverSocket = new ServerSocket(4321);
    	System.out.println("(... expecting connection ...)");
    	while (count <5){ // 5 electorates 
    		
    		
    		Socket clientSocket = serverSocket.accept(); 
    		sockets.add(clientSocket);
    		System.out.println((count+1)
    				+ " voter");
    		count ++; 
    	}
    	System.out.println("....creating....");
    	
    	ArrayList<PrintWriter> printers = new ArrayList<>();
    	ArrayList<BufferedReader> readers = new ArrayList<>();
    	
    	for (int i=0; i < sockets.size();i++){
    		Socket clientSocket = sockets.get(i); 
    		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
    		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		printers.add(out);
    		readers.add(in); 
    	}
    	
    	System.out.println("....asking to vote....");
    	for (int i=0; i < sockets.size();i++){
    		Socket clientSocket = sockets.get(i); 
    		PrintWriter out = printers.get(i);
    		
    		out.println("Voter " + i + ", please vote A or B");
    		
    		
    	}
    	
    	ArrayList<String> vote = new ArrayList<>();
    	boolean voting = true; 
    	while(voting){
        	for (int i=0; i < sockets.size();i++){
        		BufferedReader in = readers.get(i); 
        		if (in.ready()){
        			String votes = in.readLine();
        			System.out.println("Server received voter " + i + " 's vote of "  + (votes)) ; 
        			vote.add(votes); 
        		}
        	}
        	if (vote.size()==5){
        		voting = false;
        	}
    	}
    	
    	//computation of votes 
    	int A = 0; 
    	int B =0 ; 
    	for (String s:vote){
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
    	
    	//sending out the results 
    	boolean checking = true; 
    	ArrayList<String> checker = new ArrayList<>(); 
    	
        for (int i=0; i < sockets.size();i++){
        		BufferedReader in = readers.get(i); 
        		PrintWriter out = printers.get(i);
        		out.println("Would you like to know the winner? if yes, return a 'y'");
        	}	
        	
        while (checking){
        	for (int i=0; i < sockets.size();i++){
        		BufferedReader in = readers.get(i); 
        		PrintWriter out = printers.get(i);
        		if (in.ready()){
        			checker.add("lol"); 
        			System.out.println("Someone has voted.");
        			
        			if (in.readLine().equals("y")){
        				out.println("Winner is " + result);
        			}
        			else{
        				out.println("You have requested not to see the winner. Thank you for voting."); 
        			}
        		}
        	}
        	if (checker.size()==5){
        		checking = false;
        	}
    		
    	}
        System.out.println("(... closing connection ...)");
    	//closing the voters 
//    	for (int i=0; i < sockets.size();i++){
//    		
//    		PrintWriter out = printers.get(i);
//    		out.println("Bye.");
//    		Socket clientSocket = sockets.get(i); 
//    		clientSocket.close();
//    		
//    	}
        serverSocket.close();
    	
    	
	}

}
