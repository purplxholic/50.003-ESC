package Week5;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
 
public class FactorPrimeClientMul {
	public static void main(String[] args) throws Exception {
    	String hostName = "localhost";
        //String hostIP = "10.11.3.28";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;
 
//        Socket echoSocket = new Socket(hostName, portNumber);
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
        
        
        boolean doing = true;

        try{
        	System.out.println("OK");
            String fromServer = in.readLine();
        

            System.out.println("Factory: " + "Calculating");

            BigInteger[] i = division_threading(fromServer, in);
            System.out.println("I'm done!: " + i.length);

            String userInput = "";

            if (i[0] != null){
	            for (BigInteger bi : i){
	            	int n = bi.intValue(); 
	            	userInput += String.valueOf(n) + " ";
	            }
            }
            else{
            	userInput = " "; 
            }

            out.println(userInput);


          
        }
        
        catch (Exception e){
        	e.printStackTrace();
        }
        finally{
        echoSocket.close();
        in.close();
        out.close();
        stdIn.close();    
        }
    }
    public  static BigInteger[] division_threading(String string, BufferedReader br) throws IOException{
    	BufferedReader in = br; 
        BigInteger[] result = new BigInteger[2];
        BigInteger[] parsed = new BigInteger[3];
        String[] parsedString = string.split(" ");
        for (int i=0;i < parsedString.length;i++){
            parsed[i] = new BigInteger(parsedString[i]);
        }
        //2nd is the starting number, 3rd is the step to take
        BigInteger number = parsed[0]; 
        BigInteger divider = parsed[1]; 
        BigInteger step = parsed[2]; 
        boolean checker = true;
        System.out.println(step);
        System.out.println(divider);
        System.out.println(number);
        while (checker){
        	if (in.ready()) {
        		String fromServer = in.readLine(); 
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;
        	}
        	//System.out.println(divider);
            if (number.mod(divider).equals(BigInteger.ZERO)){
                result[0] = divider; 
                result[1] = number.divide(divider); 
                checker = false; 
            }
            else{
                divider=divider.add(step); 
            }
        }

        return result;
    }
}
