package com.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixRunnable {
    public static void main(String[] args) throws Exception {
		int n = 50;
		int[][] A = RandomArray(n);
		int[][] B = RandomArray(n);
		int[][] globalResult = new int[A.length][A.length];

		for (int i=1; i < 11;i++){
			int NumOfThreads = i;
			int count = 0;
			ArrayList<Natsu> threads = new ArrayList<>();
			int pos = 0;
			long startTime = System.currentTimeMillis();

			int currentList = 0 ;
			ArrayList<ArrayList<Integer[]>> megaList = new ArrayList<>();
			while (count < NumOfThreads){
				megaList.add(new ArrayList<Integer[]>()); //adding in empty ArrayLists to contain Integer[]
//			System.out.println("created " + (count+1) +  " list threads");
				count++;
			}
			for (int row=0;row < A.length; row++){
				for (int col=0; col < A.length;col++){
					List<Integer> a = new ArrayList<>();
					a.add(row);
					a.add(col);
					Integer[] b = {row,col};
					megaList.get(currentList).add(b);
//				System.out.println(currentList);
					currentList += 1;
					if (currentList == NumOfThreads){
						currentList=0;
					}
				}
			}

			ArrayList<Thread> threads1 = new ArrayList<>();

			for (int ji=0; ji<megaList.size();ji++){
//				System.out.println("creating Natsus");
				threads1.add(new Thread(new Natsu(megaList.get(ji),A,B,globalResult)));
			}


			for (Thread thread:threads1){
				thread.start();
			}


			try {
				for (Thread thread:threads1){
					thread.join();
				}
				long stopTiming = System.currentTimeMillis();
				long runningTime = stopTiming - startTime; //will get in ms
//				PrintMatrix(globalResult);
				System.out.println("Running time is " + runningTime + " milliseconds\n");


//			System.out.println("The sum is: " + (summer1.getSum() + summer2.getSum()));
			}
			catch (InterruptedException e) {
				System.out.println("A thread didn't finish!");
			}
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
	//generate matrix to test 
	private static int[][] RandomArray(int n) {
		int[][] randomMatrix = new int [n][n];

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Integer r = rand.nextInt()% 100;
				randomMatrix[i][j] = Math.abs(r);
			}

		}

		return randomMatrix;
	}
}

class Natsu implements Runnable {
	private int[][] A;
	private int[][] B;
	private int[][] result;
	private ArrayList<Integer[]> coord;
	private int lower;
	private int upper;

	public Natsu(ArrayList<Integer[]> coord, int[][] first, int[][] second, int[][] global) {
		this.coord = coord;
		A = first;
		B = second;
		result = global;
	}

	public void run() {
		for (Integer[] l : coord){
			int row = l[0];
			int col = l[1];
			for (int i=0; i < A.length;i++){
				result[row][col] += A[row][i] * B[i][col];
			}
		}

//		for(int k = 0; k < B.length; k++)
//		{
//			result[lower][upper] += A[lower][k] * B[k][upper];
//		}
	}

    	
    	public int[][] getSum() {
    		return result;
    	}
}
