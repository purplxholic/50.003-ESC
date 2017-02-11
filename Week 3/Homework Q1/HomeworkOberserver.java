package com.example;

import java.io.File;
import java.util.ArrayList;



public class HomeworkOberserver {

    public static void main(String[] args) {
        StockGrabber s = new StockGrabber();
        ///first test/////
        StockGrabber forAndy = new StockGrabber();
        ArrayList<String> whatyouWant = new ArrayList<>();
        whatyouWant.add("aapl");
        whatyouWant.add("google");
        StockObserver a = new StockObserver(forAndy,whatyouWant,"andy");

        ////second test/////
        StockGrabber s1 = new StockGrabber();
        ArrayList<String> whatyouWant2 = new ArrayList<>();
        whatyouWant2.add("aapl");
        whatyouWant2.add("google");
        StockObserver b = new StockObserver(s1,whatyouWant2,"sally");
        ////third test////
        StockGrabber s2 = new StockGrabber();
        ArrayList<String> stockName = new ArrayList<>();
        stockName.add("aapl");
        stockName.add("ibm");
        StockObserver c = new StockObserver(s2,stockName,"Ben");

        s.register(a);
        s.register(b);
        s.register(c);

        s.setAAPLPrice(1.0);
        s.setGOOGPrice(3.0);
        s.setIBMPrice(10.0);

        s.unregister(a);
        s.unregister(c);

        s.setGOOGPrice(6.0);

        s.register(a);

        s.setAAPLPrice(11.0);

    }
}
