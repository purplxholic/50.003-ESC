package com.example;

import java.util.ArrayList;
import java.util.Hashtable;

//Uses the Subject interface to update all Observers

public class StockGrabber implements iSubject {
	
	private ArrayList<Observer> observers;
	private Hashtable preference;
	private double ibmPrice;
	private double aaplPrice;
	private double googPrice;
	public String stateOfChange;

	public StockGrabber(){
		
		// Creates an ArrayList to hold all observers
		
		observers = new ArrayList<Observer>();
		preference = new Hashtable();
//		stateOfChange = new ArrayList<>();
	}
	
	public void register(Observer newObserver) {
		//take in new variables and maybe update list
		// Adds a new observer to the ArrayList
		
		observers.add(newObserver);
//		System.out.println("Success!");

		
	}

	public void unregister(Observer deleteObserver) {
		
		// Get the index of the observer to delete
		
		int observerIndex = observers.indexOf(deleteObserver);
		
		// Print out message (Have to increment index to match)
		
		System.out.println("Observer " + (observerIndex+1) + " deleted");
		
		// Removes observer from the ArrayList
		
		observers.remove(observerIndex);
		
	}

	public void notifyObserver(String stateChange) {
		
		// Cycle through all observers and notifies them of
		// price changes
		
		for(Observer observer : observers){

			//changed the interface to accept the state change
			observer.update(ibmPrice, aaplPrice, googPrice,stateChange);
			
		}
	}
	
	// Change prices for all stocks and notifies observers of changes
	
	public void setIBMPrice(double newIBMPrice){
		
		this.ibmPrice = newIBMPrice;
		System.out.println("IBM prices have changed.\n");
		stateOfChange = "ibm";
		notifyObserver(stateOfChange);
		
	}
	
	public void setAAPLPrice(double newAAPLPrice){
		
		this.aaplPrice = newAAPLPrice;
		System.out.println("AAPL prices have changed.\n");
		stateOfChange = ("aapl");
		notifyObserver(stateOfChange);
		
	}

	public void setGOOGPrice(double newGOOGPrice){
	
		this.googPrice = newGOOGPrice;
		System.out.println("Google prices have changed.\n");
		stateOfChange = ("google");
		notifyObserver(stateOfChange);
	
	}
	
}
