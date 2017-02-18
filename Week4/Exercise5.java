package Week4;

//black box test 

public class Exercise5 {
	int input;
	public Exercise5(){
		
	}
	
	public Exercise5(int a){
		this.input = a; 
		check(input);
	}
	
	public static int check(int fromInput){
		int ans = 0;
		if (fromInput == 0){
			System.out.println("0");
			ans = 0;
		}
		else if (fromInput == 9413){
			System.out.println("the cake is a lie - portal 2");
			ans = 13;
		}
		else if (fromInput <0){
			System.out.println("-1");
			ans = -1;
		}
		else if (fromInput >0){
			System.out.println("1");
			ans = 1;
		}
		else{
			System.out.println("Wrong input");
		}
		return ans;
	}
}
