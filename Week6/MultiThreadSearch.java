
public class MultiThreadSearch {
	public static boolean found = false; //so that the threads can talk to each other 
	public static int finder =0;
	public static void main(String args[]){
		int[] array = {3,8,1,2,10,4,6,7}; 
		
		
		Elementer thread1 = new Elementer(4,array,1);
		Elementer thread2 = new Elementer(4,array,2); 
		thread1.start();
		thread2.start();
		while(!found){
			System.out.println("FINDING");
//			if (thread1.isAlive()==false){
//				finder = 1;
//				found = true;
//			}
//			if (thread2.isAlive() == false){
//				finder = 2;
//				found = true;
//			}
		}
		System.out.println("Thread " + finder + " found the answer!");
	}
}

class Elementer extends Thread{
	int number;
	int[] numArray;
	int id; 
	public Elementer(int numberTobeFound,int[] numArray,int ID){
		this.number = numberTobeFound;
		this.numArray = numArray;
		this.id = ID; 
	}
	
	public void run(){
		for (int i=0;i < numArray.length;i++){
			if(numArray[i] == number ){
				MultiThreadSearch.found = true; 
				MultiThreadSearch.finder = this.id;
				break;  //terminate the thread 
			}
		}
	}
}
