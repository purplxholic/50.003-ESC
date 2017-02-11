package com.example;

//Represents each Observer that is monitoring changes in the subject

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

public class StockObserver implements Observer {

	private double ibmPrice;
	private double aaplPrice;
	private double googPrice;

	//to store what the user wants to watch
	public Hashtable<String, ArrayList<String>> choice;

	//name of user to use as a key
	private String name;

	// Static used as a counter

	private static int observerIDTracker = 0;

	// Used to track the observers

	private int observerID;

	// Will hold reference to the StockGrabber object

	private iSubject stockGrabber;

	public StockObserver(iSubject stockGrabber, ArrayList<String> userChoice, String userName) {

		choice = new Hashtable();
		// Store the reference to the stockGrabber object so
		// I can make calls to its methods

		this.stockGrabber = stockGrabber;

		// Assign an observer ID and increment the static counter

		this.observerID = ++observerIDTracker;

		// Message notifies user of new observer
		this.name = userName;
		System.out.println("New Observer " + this.observerID + " : " + userName + "\n");

		//save observer's choices
		saveUser(userName, userChoice);
//		choice.put(this.observerID,userChoice);
//		System.out.println(userChoice.get(0));

		// Add the observer to the Subjects ArrayList

		stockGrabber.register(this);


	}

	//save user choice
	private void saveUser(String usersName, ArrayList<String> stockChoice) {
		choice.put(usersName, stockChoice); //so if andy wants ibm and google, it's here
//		for (String string: choice.keySet()){
//			System.out.println(string + " " + choice.get(string));
//		}
	}

	private void deleteUser(){

	}
	// Called to update all observers

	public void update(double ibmPrice, double aaplPrice, double googPrice, String change) {
		//check whether the observer is subscribed to the stock use Hashmap choice
		//and then update them
		this.ibmPrice = ibmPrice;
		this.aaplPrice = aaplPrice;
		this.googPrice = googPrice;

		printThePrices(change);

	}

	public void printThePrices(String changeInStock) {
		String tobePrinted = "";

//		System.out.println(choice.size());
		String key = this.name;
//		if (choice.get(key) != null) {

		/*
		Type 1 of code:
		This code will inform the subscribed observers and will only print out the
		 changed stock. see type 2 below the code
		 */
			if (choice.get(key).contains(changeInStock) && choice.get(key) != null) {
				tobePrinted += "For " + key + ":";

				if (changeInStock.equals("ibm")) {
					tobePrinted += "\nIBM: " + ibmPrice;

				} else if (changeInStock.equals("google")) {
					tobePrinted += "\nGOOG: " + this.googPrice;
				} else if (changeInStock.equals("aapl")) {
					tobePrinted += "\nAAPL: " +
							aaplPrice;
				} else {
					System.out.println("ERROR RETRIEVING");
				}

			}
//		}
		else{
			System.out.println("You did not subscribe to " + changeInStock+", " + key);
		}
		tobePrinted += "\n-------------\n";
		System.out.println(tobePrinted);

	}
}

/*
		Type 2
		This code will print out the current stock prices to the observers' subscribed stocks,
		no matter whether the
		observer has been subscribed to the changed stock;
		there are 2 version:
		using hashmap .contains() to analyse or using arraylist.get().equals()
		type 2 code will not need the variable changeInStock


		int counter = 0;
		int counter2 = 0;
		for (String key: choice.keySet()){
//				if (choice.get(key)!= null) {
////					System.out.println(key + " " + choice.get(key));
//					tobePrinted += key;
//					tobePrinted += " ";
//					ArrayList<String> temp = new ArrayList<>();
//					temp = choice.get(key);
//
////					System.out.println(choice.get(key).contains("ibm") && choice.get(key).contains("google"));
////					System.out.println("temp: " + temp);
////////////////////USING .CONTAINS() BOOLEAN///////////////////////////////
////					for (int i = 0; i < temp.size(); i++) {
////						if (choice.get(key).contains("ibm")) {
////							tobePrinted += "\nIBM: " + ibmPrice;
////							tobePrinted += "\nGOOG: " + googPrice;
////						} else if (choice.get(key).contains("google")) {
////							tobePrinted += "\nGOOG: " + googPrice;
////						} else if (choice.get(key).contains("aapl")) {
////							tobePrinted += "\nAAPL: " +
////									aaplPrice;
////						} else {
////							System.out.println("ERROR RETRIEVING");
////						}
//////////////////////USING ARRAYLIST///////////////////////
//					while (counter < temp.size()-1) {
//						for (int i = 0; i < temp.size(); i++) {
//							if (temp.get(i).equals("ibm")) {
//								tobePrinted += "\nIBM: " + ibmPrice;
//							} else if (temp.get(i).equals("google")) {
//								tobePrinted += "\nGOOG: " + googPrice;
//							} else if (temp.get(i).equals("aapl")) {
//								tobePrinted += "\nAAPL: " +
//										aaplPrice;
//							} else {
//								System.out.println("ERROR RETRIEVING");
//							}
//						}
//						counter ++ ; //go to the next user
//					}
//				}
//////////////////START OF COMMON ENDING CODE///////////
//				else{
//					System.out.println("You did not subscribed to any stocks");
//				}
//		System.out.println(tobePrinted+ "\n-----------\n");
//			}
		 */

////////////////////DRAFT CODE/////////////////////////

//		for (String string: holder){
//			if (string.equals("ibm")){
//				tobePrinted += "\nIBM: " + ibmPrice;
//			}
//			else if (string.equals("google")){
//				tobePrinted += "\nGOOG: " + googPrice;
//			}
//			else if (string.equals("aapl")){
//				tobePrinted += "\nAAPL: " +
//						aaplPrice;
//			}
//			else {
//				System.out.println("ERROR RETREIVING");
//			}
//		}
//		System.out.println(observerID + tobePrinted);
//		System.out.println(observerID + "\nIBM: " + ibmPrice + "\nAAPL: " +
//				aaplPrice + "\nGOOG: " + googPrice + "\n");
		
//	}
	

