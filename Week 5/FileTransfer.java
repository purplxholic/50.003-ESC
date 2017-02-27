package Week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransfer2 {
	public static int count =0;
	public static void main(String args[]) throws Exception{
		ServerSocket serverSocket = new ServerSocket(4321);
		String toSave =  "";
		while (true){
			Socket clientSocket= serverSocket.accept(); 
			System.out.println("New client connected!");
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
			String input ;
			while (true){
				input = in.readLine();
				System.out.println("Client: "+ input);
	    		if (input.equals("done")){
	    			out.println("bye");
	    			break;
	    		}
	    		
	    		
	        			toSave += " " +input + "\n" ; 
	        			out.println("ok");
	        	
    		
			}
			System.out.println("exporting");	
    		EXPORTTHISSHIT(toSave);
    		System.out.println("exported");
    		count ++; 
    		toSave = ""; //reset the string to export out 
    		clientSocket.close();
    		in.close();
    		out.close();
		}
		
	}
	
	public static void EXPORTTHISSHIT(String n){
		
		try{
            PrintWriter printWriter = new PrintWriter("out"+ count +".txt","UTF-8");
//            for (Integer i:n){
//            printWriter.print(i + " , ");
//            }
            printWriter.println(n);
            printWriter.close();
            
        }
        catch (IOException e){
            e.printStackTrace();
        }
	}
}
