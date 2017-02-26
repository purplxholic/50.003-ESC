
package Week5;
import java.net.*;
import java.io.*;
 
public class EchoServer {
    public static void main(String[] args) throws Exception {
    	ServerSocket serverSocket = new ServerSocket(4321);
    	System.out.println("(... expecting connection ...)");
        Socket clientSocket = serverSocket.accept();     // 1 socket = 1 client 
        System.out.println("(... connection established for husband ...)");
        Socket clientSocket2 = serverSocket.accept();
        System.out.println("(... connection established for son...)");
        Socket clientSocket3 = serverSocket.accept();
        //so if there's multiple sockets, means multiple climent supported 
    	System.out.println("(... connection established for daughter...)");
    	System.out.println("(... connection established ...)");
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);           
        PrintWriter out2 =
                new PrintWriter(clientSocket2.getOutputStream(), true); 
        PrintWriter out3 =
                new PrintWriter(clientSocket3.getOutputStream(), true); 
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(clientSocket2.getInputStream()));
        BufferedReader in3 = new BufferedReader(
                new InputStreamReader(clientSocket3.getInputStream()));
        
        String inputLine = "";
        //the server is whatever the husband is saying 
        boolean doing = true; 
        while (doing){
        	if (!(inputLine = in.readLine()).equals("Bye!")){
        		System.out.print("Wife says: "+ inputLine);
        		out.println(stdIn.readLine());
        		 out.flush();
                 
        	}
        	else{
        		doing = false;
        	}
        	if (!(inputLine = in2.readLine()).equals("Bye!")){
        		System.out.print("Son says: "+ inputLine);
        		out2.println(stdIn.readLine());
        		out2.flush();
        	}
        	else{
        		doing = false;
        	}
        	if (!(inputLine = in3.readLine()).equals("Bye!")){
        		System.out.print("Daughter says: "+ inputLine);
        	    out3.println(stdIn.readLine());
        		out3.flush();
        	}
        	else{
        		doing = false;
        	}
        }
        out.println(inputLine);            
        serverSocket.close();
        clientSocket.close();
        clientSocket2.close();
        clientSocket3.close();
        out.close();
        out2.close();
        out3.close();
        in.close();
        in2.close();
        in3.close();
//        while (!((inputLine = in.readLine()).equals("Bye"))&&!((inputLine = in2.readLine()).equals("Bye"))&&!((inputLine = in3.readLine()).equals("Bye"))) {
//        	System.out.println("Wife says:" + inputLine); //server will send this to the client 
//            
//        	out3.println(stdIn.readLine());
//            out2.println(stdIn.readLine());
//            out.println(stdIn.readLine());
//            
//            out3.flush();
//            out2.flush();
//            out.flush();
//            
//            
//        }
       
    }
}
/*
 * server is actually is the husband ! 
 * the original client is the wife . because the message from client is being sent to the server, who is waiting for it 
 * when in cilent1 console keys in message, it belongs to wife 
 * when in client 2, it's son 
 * husband will need to reply for the other affected console to get their respective messages from the dad 
 * keying in the server console is sending something out to the husband 
*/
