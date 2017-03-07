import java.util.ArrayList;

public class HWQn2 extends Thread{
	public static void main(String args[]){
		StringBuffer sb = new StringBuffer("A"); 
		int numofthreads =3; 
		ArrayList<Thread> threadss = new ArrayList<>(); 
		for (int i=0;i<numofthreads;i++){
			threadss.add(new HWQn2(sb)); 
		}
		
		for (int j=0; j<threadss.size();j++){
			threadss.get(j).start();
		}
		System.out.println("Done!");
	}
	
	StringBuffer string;
	int count = 100; 
	char[] charac = {'B','C'} ; 
	int countcharac = 0; 
	public HWQn2(StringBuffer string){
		this.string = string; 
		
	}
	
	public void run(){
		synchronized(this.string){
			
				while (count != 0){
					System.out.println(this.string);
					count --; 
//					countcharac++; 
				}
				if (string.charAt(0) == 'A'){
//					System.out.println("no of A: " + countcharac);
					countcharac=0;
					string.setCharAt(0, 'B');
				}
				else if(string.charAt(0) == 'B'){
//					System.out.println("no of B: " + countcharac);
					countcharac=0;
					string.setCharAt(0, 'C');
				}
				else{
//					System.out.println("no of C: " + countcharac);
				}
				
				
			}
		
	}
}

class Letter extends Thread{
	StringBuffer string;
	int count = 100; 
	char[] charac = {'B','C'} ; 
	int countcharac = 0; 
	public Letter(StringBuffer string){
		this.string = string; 
		
	}
	
	public void run(){
		synchronized(this.string){
			while (countcharac!= charac.length){
				while (count != 0){
					System.out.println(this.string.toString());
					count --; 
				}
				string.setCharAt(0, charac[countcharac]);
				countcharac++; 
			}
		}
		
	}
}
