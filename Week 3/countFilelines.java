package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by The Gt Zan on 10-Feb-17.
 */

public class countFilelines {
    public static void main(String[] args) {
        ArrayList<File> listOfFiles = new ArrayList<>();
        int numberOfLines =0;
        //create list of files
        listOfFiles.add(new File("C:\\Users\\The Gt Zan\\Documents\\50.003 Software\\forqn4.txt")); //file path, should have 3 lines
        listOfFiles.add(new File("C:\\Users\\The Gt Zan\\Documents\\50.003 Software\\qn4part2.txt")); //should have 6 lines
        for (File f: listOfFiles){

            try {
                FileReader fileReader = new FileReader(f);
                LineNumberReader reader = new LineNumberReader(fileReader);
                while (reader.readLine()!=null){
                    numberOfLines++;
                }
                System.out.println("Total Lines: " + numberOfLines);
                reader.close();
            }
            catch (Exception e){
                System.out.println("No such file found!");
            }
            finally {
                numberOfLines =0;
            }
        }
    }

}
