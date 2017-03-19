package Week8;

public class MyStackThreadSafe {
	private final int maxSize;
	private long[] stackArray; //guarded by "this"
	private int top; //invariant: top < stackArray.length && top >= -1; guarded by "this"	
	
	//pre-condition: s > 0
	//post-condition: maxSize == s && top == -1 && stackArray != null
	public MyStackThreadSafe(int s) { //Do we need "synchronized" for the constructor?
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}
	
	//pre-condition: top >= 0
	//post-condition: the top element is removed
	public synchronized long pop() { //sync (to this actually) cos stackArray and top are related 
		long toReturn; 
		
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		toReturn = stackArray[top--];
		notifyAll();			
	    return toReturn;
	}	

	//pre-condition: true
	//post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public synchronized boolean isEmpty() {
		return (top == -1);
	}
	
	//cohort 
	//pre-condition: top>=0 
	//post-condition: there is a new long variable in the stack and the rest of the elements are unchanged 
	public synchronized void push(long j) {
		while (isFull()){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		stackArray[++top] = j;
		notifyAll(); 
	}
	
	//pre-condition: there must be items in the stack
	//post-condition: returns a long variable and elements remain unchanged
	public synchronized long peek() {
		long peekReturn; 
		peekReturn = stackArray[top]; 
		notifyAll(); 
	    return peekReturn;
	}
	
	//pre-condition: false 
	//post-condition: elements remain un-changed. returns true iff the stack is at its max capacity 
	public synchronized boolean isFull() {
		return (top == maxSize - 1);
	}
}