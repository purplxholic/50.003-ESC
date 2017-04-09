package Week11;

import java.util.HashSet;
import java.util.Set;

public class DLExample {
	
}
/*
 * it is deadlocking. explanation are given within the code 
 */
//looks at where need at least two locks to get a deadlock and are needed to be opened in different ways 
class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

	public synchronized Point getLocation() {
        return location;
    }
	//will have lock on the taxi object,first lock  
    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination))
            dispatcher.notifyAvailable(this); // calling the method when you're holding onto one lock 
        //leads to a thread holding onto 2 locks -> deadlock will occur because another thread can call for locks
        //the other way round 
    }

    public synchronized Point getDestination() {
        return destination;
    }
}

class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }
    //second lock 
    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }
    //need a lock on the object ,
    //thread will first get lock from dispatcher, then from taxi 
    //if another thread setLocation: grab from taxi then dispatcher 
    //deadlock 
    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation()); //getLocation is syn again 
        return image;
    }
}

class Image {
    public void drawMarker(Point p) {
    }
}

class Point {
	
}

