package Week5;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;

public class ChatRoom {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(4321);
//			ArrayList<Socket> sockets = new ArrayList<>();
//			ArrayList<PrintWriter> printers = new ArrayList<>();
//        	ArrayList<BufferedReader> readers = new ArrayList<>();
        	int count = 0; 
			while (true){
				Socket clientSocket= serverSocket.accept(); 
//				System.out.println("Client " + count +" has joined!");
//				count++;
				
        		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        		String input = in.readLine(); 
        		System.out.println("Client: "+ in.readLine());
        		clientSocket.close();
        		in.close();
        		
//        		for (int i=0; i < sockets.size();i++){
//        			BufferedReader in2 = readers.get(i);
////        			System.out.println(readers.size());
////        			System.out.println("CHECKING");
//        			String input = in2.readLine();
//        			System.out.println(input);
//        			if (input.equals(null)){
//        				System.out.println("Client " +(i+1) +": "+ input);
//        			}
//        			while (true){
//        				if (in2.equals("\\n")){
//        					System.out.println("Client " +(i+1) +" Goodbye!");
//        					printers.get(i).println("Bye.");
//        					printers.remove(i);
//        					readers.remove(i); 
//        					sockets.get(i).close();
//        				}
//        				else if (in2.equals(null)){
//        					break; 
//        				}
//        				else{ 
//        					System.out.println("Client " +(i+1) +": "+ input);
//        					break; 
//        				}
//        			}
//        			if (in2.ready()){
//        				if (in2.equals("\\n")){
//        					System.out.println("Client " +(i+1) +" Goodbye!");
//        					printers.remove(i);
//        					readers.remove(i); 
//        					sockets.get(i).close();
//        				}
//        				else{ 
//        					System.out.println("Client " +(i+1) +": "+ input);
//        				}
//        			}
        			
        			
        		}
        		 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

