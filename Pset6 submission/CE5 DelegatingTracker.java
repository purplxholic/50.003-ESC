package Week8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; //this is thread-safe!
import java.util.concurrent.ConcurrentMap; //this is thread-safe!

//is this class thread-safe?
/*
 * yes thread safe as all methods are synchronized and locations is unmodifiable 
 */
public class DelegatingTracker {
	private final ConcurrentMap<String, Point> locations;
	
	public DelegatingTracker(Map<String, Point> locations) {
		this.locations = new ConcurrentHashMap<String, Point>(locations);
	}
	
	public synchronized Map<String, Point> getLocations () {
		return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
		//Here, there isn't a need to fix as 
		//there will be a return of new hashmap, and it will be unmodified and thus will be safe 
		//so if you wanna share obj, make sure it's a clone or cannot be modify, like using this class
	}
	
	//is this an escape?
	//This will not be an escape as the unmodifiable property of locations 
	//will allow sharing of the object possible and it cannot be modified/ref by another thread 
	public synchronized Point getLocation (String id) {
		return locations.get(id);
	}
	
	public synchronized void setLocation (String id, int x, int y) {		
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		locations.get(id).set(x, y);
	}
	
	//is this class not thread-safe?
	/*
	 * Yes it is thread safe as you can only set x and y together
	 * set and get are synchronized so only one thread can do it at a time 
	 */
	//is a Point object mutable?
	/*
	 * Yes it is mutable as there is a set method 
	 */
	class Point {
		private int x, y; //make these private! 
		
		private Point (int[] a) { 
			this(a[0], a[1]);
		}
		
		public Point (Point p) {
			this(p.get());
		}
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public synchronized int[] get() {
			return new int[] {x, y};
		}
		
		public synchronized void set(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
