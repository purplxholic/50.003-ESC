package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ChatRoomClient2 {
	public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        //String hostIP = "10.11.3.28";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;
 
//        Socket echoSocket = new Socket(hostName, portNumber);
		
        
//        BufferedReader in =
//                new BufferedReader(
//                    new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        String userInput ; 
        String fromServer ;
        boolean doing = true;
        do {
        	Socket echoSocket = new Socket();
    		SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber);
    		echoSocket.connect(sockaddr, 100);
    		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            userInput = stdIn.readLine();
            
            System.out.println("Client: " + userInput); 
            out.println(userInput); //sending to server 
            out.flush();
            System.out.println("message sent!" ); //read message from server/wei ren 

//            echoSocket.close();
//            out.close();
            
        } while (!userInput.equals("Bye.")); 
        

        
       
        
        stdIn.close();    
//        }
    }
}
