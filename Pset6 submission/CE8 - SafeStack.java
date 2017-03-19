package Week8;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class tester{

	public static void main(String args[]){
		long startTime = System.currentTimeMillis(); 
		SafeStack safeStack = new SafeStack(10); 
		ArrayList<S1> threads1 = new ArrayList<>(); 
		System.out.println("START TEST");
		for (int i=0;i<3;i++){
			threads1.add(new S1(safeStack));
		}
		System.out.println("DONE ADDING");
		for (int i=0; i< threads1.size();i++){
			threads1.get(i).start();
		}
		
		for (int i=0; i< threads1.size();i++){
			try {
				threads1.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long endTime= System.currentTimeMillis(); 
		long totalTime = endTime - startTime; 
		System.out.println("Total time for first stack in ms: " + totalTime );
		
		long startTime2 = System.currentTimeMillis(); 
		SafeStack2 safeStack2 = new SafeStack2(10); 
		ArrayList<S2> threads2 = new ArrayList<>(); 
		System.out.println("START TEST");
		for (int i=0;i<3;i++){
			threads2.add(new S2(safeStack2));
		}
		System.out.println("DONE ADDING");
		for (int i=0; i< threads1.size();i++){
			threads2.get(i).start();
		}
		
		for (int i=0; i< threads1.size();i++){
			try {
				threads2.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long endTime2= System.currentTimeMillis(); 
		long totalTime2 = endTime2 - startTime2; 
		System.out.println("Total time for second stack in ms: " + totalTime2 );
	}
}

class S1<E> extends Thread{
	SafeStack<E> safeStack; 
	public S1(SafeStack<E> Ss){
		safeStack = Ss; 
	}
	
	public void run(){
		//System.out.println(safeStack.stackSize);
		
		for (int j=0;j<10000;j++){
			Random random = new Random();
			Integer k = random.nextInt(3);
//			System.out.println(k);
			if (k ==1){
//				System.out.println("POPPING");
				safeStack.popIfNotEmpty();
				
			}
			else{
//				System.out.println(safeStack.pushIfNotFull((E) k));
				safeStack.pushIfNotFull((E) k);
				
			}
			 
			
		}
	}
}
class S2<E> extends Thread{
	SafeStack2<E> safeStack2; 
	public S2(SafeStack2<E> Ss){
		safeStack2 = Ss; 
	}
	
	public void run(){
		//System.out.println(safeStack2.stackSize);
		
		for (int j=0;j<10000;j++){
			Random random = new Random();
			Integer k = random.nextInt(3);
//			System.out.println(k);
			if (k ==1){
//				System.out.println("POPPING");
				safeStack2.popIfNotEmpty();
				
			}
			else{
//				System.out.println(safeStack2.pushIfNotFull((E) k));
				safeStack2.pushIfNotFull((E) k);
				
			}
			 
			
		}
	}
}

class SafeStack<E> extends Stack<E> {
	
	private Stack<E> stack = new Stack<E>();
	public int stackSize; 
	
	public SafeStack(){
		this.stackSize = 1000; 
	}
	
	public SafeStack(int n){
		this.stackSize = n ; 
	}
	public synchronized boolean pushIfNotFull(E e){
		
		if (stack.size() < stackSize){
			stack.push(e); 
			return true; 
		}
		else{
			return false; 
		}
	}
	
	public synchronized E popIfNotEmpty(){
		if (isEmpty()){
			return null;
		}
		else{ 
			return stack.pop(); 
		}
		
	}
	
	//returns true if it is empty, else false 
	public synchronized boolean isEmpty(){
		return stack.empty(); 
	}
	public synchronized boolean isFull(){
		if (stack.size() == stackSize){
			return true;
		}
		else{
			return false; 
		}
	}
}


//follow ImprovedList 
class SafeStack2<E> extends Stack<E>{
	
	private Stack<E> stack = new Stack<E>();
	public  int stackSize; 
	public SafeStack2(){
		 this.stackSize = 1000; 
	}
	
	public SafeStack2(int n){
		this.stackSize = n; 
	}
	public  boolean pushIfNotFull(E e){
		synchronized(stack){
			if (stack.size() < stackSize){
				push(e); 
				return true; 
			}
			else{
				return false; 
			}
		}
	}
	

	public E popIfNotEmpty(){
		synchronized(stack){
			if (isEmpty()){
				return null;
			}
			else{ 
				return pop(); 
			}
		}
	}
	//returns true if it is empty, else false 
		public synchronized boolean isEmpty(){
			return empty(); 
		}
	
		public synchronized boolean isFull(){
			if (stack.size() == stackSize){
				return true;
			}
			else{
				return false; 
			}
		}
		
		public synchronized E push(E e){
			return this.stack.push(e); 
		}
		
		public synchronized E pop(){
			return this.stack.pop(); 
		}
		
		public synchronized boolean empty(){
			return this.stack.empty(); 
		}
		
		public synchronized E peek(){
			return this.stack.peek(); 
		}
		
		public synchronized int search (Object o){
			return this.stack.search(o); 
		}
}
