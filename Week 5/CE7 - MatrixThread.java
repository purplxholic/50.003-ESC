package com.example;

public class MatrixThread {
	public static void main (String[] args) {
		int[][] A = {{2,2,2},{3,3,3}};
        int[][] B = {{3,4,5},{6,7,8}};
		int[][] globalResult = new int[A.length][A.length];

		/*
		manually construct the threads. so first thread gets rows 0 to A.length/2
		second thread gets rows A.length/2 to the max rows, which is A.length (2 rows)

		have a global result so that there isn't a need to combine the results of the threads tgt
		 */

		Thread3 thread1 = new Thread3(0,A.length/2,A,B,globalResult);
		Thread4 thread2 = new Thread4(A.length/2,A.length,A,B,globalResult);


		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();

//			System.out.println("Value of Expression: " + thread1.getValue()*thread2.getValue());
			PrintMatrix(globalResult);
		}
		catch (InterruptedException e) {
			System.out.println("A thread didn't finish!");
		}
	}

    public static void PrintMatrix (int[][] toprint) {
    	//assume that toprint is a square matrix
    	//assume that toprint is not []
    	for (int i = 0; i < toprint.length; i++) {
        	for (int j = 0; j < toprint[0].length; j++) {
        		System.out.print(toprint[i][j] + "\t");
        	}
    		System.out.println();
    	}
    }
}

//{{2,2},{2,2}} {{1,1},{1,2}}
//result= {{},{}}
class Thread3 extends Thread{
	private int[][] A;
    private int[][] B;
	private int[][] result;
    private int lower ;
    private int upper;
	public Thread3 (int lowerrow,int upperrow, int[][] first,int[][] second,int[][] global){
        this.lower = lowerrow;
        this.upper = upperrow;
		A = first;
		B = second;
		result = global;

	}
	public void run(){
        for (int i = lower; i < upper; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k]*B[k][j];            	 		}
            }
        }


    }

	public void getResult(){

	}

}

class Thread4 extends Thread{
	private int[][] A;
	private int[][] B;
	private int[][] result;
	private int lower ;
	private int upper;
	public Thread4 (int lowerrow,int upperrow, int[][] first,int[][] second, int[][] global){
		this.lower = lowerrow;
		this.upper = upperrow;
		A = first;
		B = second;
		result = global;
	}
	public void run(){
		for (int i = lower; i < upper; i++) {
			for (int j = 0; j < result[i].length; j++) {
				for (int k = 0; k < B.length; k++) {
					result[i][j] += A[i][k]*B[k][j];            	 		}
			}
		}


	}

	public void getResult(){

	}

}


