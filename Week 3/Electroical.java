package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by The Gt Zan on 10-Feb-17.
 * Write a Java program for a sequential election vote casting application as follows:
 * There are two candidates A and B contesting an election. There are five electorates a
 * nd each electorate can cast their vote only once and
 * for only one of the two candidates (A or B).
 * The five electorates cast their votes, i.e.
 * a character ‘A’ or ‘B’, one after another.
 * The winner is the candidate who gets the maximum number of
    votes.

 to myself: use visitor when i have time later
 */

public class Electroical {


    public static void main(String[] args) {
    ArrayList<Character> votes = new ArrayList<>();
        int count = 0;
        while (count<5){
            Scanner question = new Scanner(System.in);
            System.out.println("What is your vote?");
            String answer = question.nextLine(); //get the vote
            votes.add(answer.charAt(0)); //add the vote character into the list
            count++; //increase count to make sure all gets their vote
        }
        int countA =0;
        int countB =0;
        for (Character character: votes){
            if (character.equals('A')){
                countA++;
            }
            else if (character.equals('B')){
                countB++;
            }
            else{
                System.out.println("Error occurred!");
            }
        }
        System.out.println("The winner is " + Collections.max(votes));

    }



}
