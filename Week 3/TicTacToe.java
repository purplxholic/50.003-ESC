package com.example;

import java.util.Scanner;

/**
 * Created by The Gt Zan on 09-Feb-17.
 */

public class TicTacToe {
    private String prevstate; //to check the current state of the game
    private String starter;
    private String[][] board;

    public TicTacToe(){
        System.out.println("ERROR: Please choose who should go first");
    }
    public TicTacToe(String choice){
        System.out.println("Player with " + choice + " will go first.");
        board = new String[3][3];
        //print out the board for the user to use
        for (int i=0;i<3;i++){

            for (int j =0;j<3;j++){
                board[i][j] = "_"; //acually used |_| but messes stuff up

            }


        }

//        System.out.println(board[0][0]+board[0][1]+board[0][2]+"\n"+board[1][0]);
//        printBoard();
        starter = choice;

    }
//print out for the users to see
    private void printBoard(){

        for (int i = 0; i < 3; i++) {
//            System.out.println("|");
            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j] + " |");
            }

            System.out.println();

        }
        System.out.println("----NEXT MOVE----");
    }

    private void addMove(int a, int b){
        if (a <0 && b<0 && a>3 && b>3){ //check if the move is valid
            System.out.println("Invalid movement! It is still " + starter+"'s move");
        }
        else if (board[a][b].equals("_")){
            board[a][b] = starter;
            printBoard();

            if (starter.equals("x")) {
                prevstate = "x";
                starter = "o";
                System.out.println("It's now o's turn!");//next turn
            }
            else {
                prevstate="o";
                starter = "x";
                System.out.println("It's now x's turn!");
            }

        }
        else{
            System.out.println("Error has accord!");
        }

    }

    private boolean checkRowWin(){
//        int row = 0;

        for (int i=0;i<board.length;i++){
//            for (int j=0;j<board.length;j++){

                if (board[i][0].equals(board[i][1])&&board[i][0].equals(board[i][2])&&board[i][1].equals(board[i][2])&&!board[i][0].equals("_")){
//                    System.out.println("Someone won!");
                    return true;
                }
//            }
        }
        System.out.println("No Row Wins");
        return false;
    }

    private boolean checkColWin(){
        for (int i=0;i<3;i++){
//            if (board[0][1].equals(board[1][i])&&board[0][i].equals(board[2][i])&&board[1][i].equals(board[2][i])&&!board[0][i].equals("_")){
            if(board[0][i].equals(board[1][i])&&board[1][i].equals(board[2][i])&&!board[i][0].equals("_")){
//            System.out.println("Someone won!");
                return true;
            }
        }
        System.out.println("No Col Wins");
        return false;
    }
    private boolean checkDiagonalWin(){
        if (board[0][0].equals(board[1][1])&&board[1][1].equals(board[2][2])){
            return true;
        }
        if (board[0][2].equals(board[1][1])&&board[1][1].equals(board[2][0])){
            return true;
        }
        System.out.println("No Diag Wins");
        return false;

    }

    public boolean checkifWin(){
        if (checkRowWin()||checkColWin()||checkDiagonalWin()){
            System.out.println("GAME OVER! Player of " + prevstate +" has won!");
            return true;
        }
        else {
            System.out.println("The game still hasn't been won");
        }
        return false;
    }
    //allow players to place items
    public static void main(String[] args) {

        boolean game = true;
        Scanner question = new Scanner(System.in);
        System.out.println("Key in x or o for the first player");
        String firstPlayer = question.nextLine();
        //initialise the board for the first time
        TicTacToe a = new TicTacToe(firstPlayer);
        while (game){
            System.out.println("--------------------------------------");
            Scanner command = new Scanner(System.in);
            System.out.println("What would you like to do? m for insert movement\nw for checking win condition\nq for quitting");
            String commander = command.nextLine();
            System.out.println("--------------------------------------");
            if (commander.equals("m")){
                Scanner questionMove = new Scanner(System.in);
                System.out.println("Key in the coordinates");
                System.out.println("Row:");
                int row = questionMove.nextInt();
                System.out.println("Col:");
                int col = questionMove.nextInt();
                a.addMove(row,col);
            }
            else if (commander.equals("w")){
                if (a.checkifWin()){
                    game = false;
                }
                else{
                    System.out.println("No one has won");
                }
            }
            else if (commander.equals("q")){
                game = false;
            }
            else{
                System.out.println("INVALID INPUT");
            }


        }
    }


}
