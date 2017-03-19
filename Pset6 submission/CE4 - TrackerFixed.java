package Week8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

class test extends Thread {
	TrackerFixed tracker;
	
	public test (TrackerFixed tra) {
		this.tracker = tra;
	}
	
	public void run () {
		Week8.TrackerFixed.MutablePoint loc = tracker.getLocation("somestring");
		loc.x = -1212000;
	}
}

//is this class thread-safe?
public class TrackerFixed {
	//@guarded by ???
	private final Map<String, MutablePoint> locations;
	
	//the reference locations, is it going to be an escape?
	public TrackerFixed(Map<String, MutablePoint> locations) {
		this.locations = locations;
	}
	
	//is this an escape?
	public Map<String, MutablePoint> getLocations () { //must clone both mutablepoints and locations so that they're still not pointinf to the same thing 
		Map<String,MutablePoint> newMap = new HashMap<>(); 
		for (String string: locations.keySet()){
			int newX = locations.get(string).x; 
			int newY = locations.get(string).y; 
			MutablePoint mp = new MutablePoint(newX,newY); 
			newMap.put(string, mp); 
		}
		return newMap;
	}
	
	//is this an escape?
	public MutablePoint getLocation (String id) {
//		MutablePoint loc = locations.get(id); //this just creates a new link ref so need to clone also 
		int newX = locations.get(id).x;
		int newY = locations.get(id).y; 
		MutablePoint loc = new MutablePoint(newX,newY); 
		return loc;
	}
	
	public void setLocation (String id, int x, int y) { //is it safe? yes 
		MutablePoint loc = locations.get(id);
		
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		loc.x = x;
		loc.y = y;
	}
	
	//this class is not thread-safe (why?) and keep it unmodified.
	class MutablePoint {
		public int x, y;

		public MutablePoint (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public MutablePoint (MutablePoint p) {
			this.x = p.x;
			this.y = p.y;
		}
	}
	
}
