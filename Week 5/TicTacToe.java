package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TicTacToe {
	private static String[][] board;
	private static String starter ; 
	private static String prevstate; 
	

	public static void main(String[] args) throws Exception {
		/////////////////////////////TTT/////////////
		board = new String[3][3];
		for (int g=0;g<3;g++){

            for (int j =0;j<3;j++){
                board[g][j] = "_"; //actually used |_| but messes stuff up

            }
		}
            ///////////////////////////////////////
    	int count = 0; 
    	ArrayList<Socket> sockets = new ArrayList<>();
    	ServerSocket serverSocket = new ServerSocket(4321);
    	//Accept 2 Sockets only 
    	while (count <2){
    		System.out.println("(... expecting connection ...)");
    		
    		Socket clientSocket = serverSocket.accept(); 
    		sockets.add(clientSocket);
    		System.out.println("(... connection established ...)");
    		count ++; 
    	}
    	
    	System.out.println("CREATING");
    	
    	ArrayList<PrintWriter> printers = new ArrayList<>();
    	ArrayList<BufferedReader> readers = new ArrayList<>();
    	
    	for (int i=0; i < sockets.size();i++){
    		Socket clientSocket = sockets.get(i); 
    		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
    		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		printers.add(out);
    		readers.add(in); 
    	}
    	
    	System.out.println();
    	System.out.println("LET THE GAMES BEGIN");
    	System.out.println("PLEASE SEE THE SERVER CONSOLE TO SEE THE CURRENT STATUS OF THE BOARD");
    	System.out.println("COORDINATE GUIDE ; PLEASE KEY IN AS 'X Y' ");
    	System.out.println("ROW 0: 0 0 | 0 1 | 0 2\nROW 1: 1 0 | 1 1 | 1 2\nROW 2: 2 0 | 2 1 | 2 2");
    	System.out.println();
    	System.out.println("----HOW THE BOARD LOOKS LIKE----");
    	printBoard(); 
    	
    	int turns = 0; 
    	boolean playing = true; 
    	while (playing){
    		for (int j=0;j<sockets.size();j++){
    			printers.get(j).println("Please key in your movement ");
    			String input = readers.get(j).readLine();
    			printers.get(j).println("your previous movement was recorded as " + input); //talk baack to players
    			addMove(input,j);  //get the coordinates 

    			///////////
    			
    			printBoard(); 
    			turns ++; //to keep track of number of turns 
    			
        		
        		if (checkifWin() ){
        			
        			playing = false; 
        		}
        		if (turns ==9 && checkifWin() == false){
        			System.out.println("OOPS");
        			playing = false; 
        		}
        		
        		    			
    		}
    		
    	}
    	System.out.println("GAME IS BEING DESTROYED AND SERVER WILL BE CLOSING");
    	for (int i=0; i < sockets.size();i++){
    		Socket clientSocket = sockets.get(i); 
    		PrintWriter out = printers.get(i);
    		out.println("Bye.");
    		clientSocket.close();
    		
    	}	
		} 
	
	//code from earlier pset 
	
	private static void printBoard(){

		
        for (int i = 0; i < 3; i++) {
//            System.out.println("|");
            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j] + " |");
            }

            System.out.println();

        }
        System.out.println("----NEXT MOVE----");
    }
	
	private static void addMove(String movement, int playerNumber){
		starter = String.valueOf(playerNumber);
		String[] m = movement.split(" "); 
		int a = Integer.parseInt(m[0]) ; 
		int b = Integer.parseInt(m[1]) ;
        if (a <0 && b<0 && a>3 && b>3){ //check if the move is valid
            System.out.println("Invalid movement!");
        }
        else if (board[a][b].equals("_")){
            board[a][b] = starter;
        
//            printBoard();

            if (starter.equals("1")) {
                prevstate = "1";
                starter = "0";
                System.out.println("It's now 0's turn!");//next turn
            }
            else {
                prevstate="0";
                starter = "1";
                System.out.println("It's now 1's turn!");
            }

        }
        else{
            System.out.println("Error has accord!");
        }

    }
	
	private static boolean checkRowWin(){
//      int row = 0;

      for (int i=0;i<board.length;i++){
//          for (int j=0;j<board.length;j++){

              if (board[i][0].equals(board[i][1])&&board[i][0].equals(board[i][2])&&board[i][1].equals(board[i][2])&&!board[i][0].equals("_")){
//                  System.out.println("Someone won!");
                  return true;
              }
//          }
      }
//      System.out.println("No Row Wins");
      return false;
  }

  private static  boolean checkColWin(){
      for (int i=0;i<3;i++){
//          if (board[0][1].equals(board[1][i])&&board[0][i].equals(board[2][i])&&board[1][i].equals(board[2][i])&&!board[0][i].equals("_")){
          if(board[0][i].equals(board[1][i])&&board[1][i].equals(board[2][i])&&!board[0][i].equals("_")){
//          System.out.println("Someone won cols !");
              return true;
          }
      }
//      System.out.println("No Col Wins");
      return false;
  }
  private static boolean checkDiagonalWin(){
      if (board[0][0].equals(board[1][1])&&board[1][1].equals(board[2][2])&&!board[0][0].equals("_")&&!board[1][1].equals("_")&&!board[2][2].equals("_")){
//    	  System.out.println("biag 1");
          return true;
      }
      if (board[0][2].equals(board[1][1])&&board[1][1].equals(board[2][0])&&!board[0][2].equals("_")&&!board[1][1].equals("_")&&!board[2][0].equals("_")){
//    	  System.out.println("biag 1");
    	  return true;
      }
//      System.out.println("No Diag Wins");
      return false;

  }

  public static boolean checkifWin(){
      if (checkRowWin()||checkColWin()||checkDiagonalWin()){
          System.out.println("GAME OVER! Player " + prevstate +" has won!");
          return true;
      }
      else {
          System.out.println("The game still hasn't been won");
      }
      return false;
  }
  
  public static void checkBoardFull(){
	  String status = "";
	  for (int i=0; i<3;i++){
		  for (int j=0; j <3;j++){
			  if (!board[i][j].equals(null) && checkifWin()==false){
				  status = ("The game still hasn't been won but the board is full");
//				  return false;
			  }
			  else{
				  status = ("Looks like someone managed to win!");
			  }
		  }
	  }
	  System.out.println(status);
	  
//	  return true; 
  }

}
