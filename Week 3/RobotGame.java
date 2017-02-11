package com.example;

import java.util.Random;
import java.util.zip.Inflater;

class Robot {
	String name;
	Robot robot;
	IBehaviour behaviour;
	public Robot(){

	}
	public Robot (String name)
	{
		this.name = name;
	}

	public void behave (String command)
	{
		//the robots behave
		if (command.equals("Move")){
			System.out.println(Integer.toString(behaviour.moveCommand()));
		}
		else if (command.equals("Attack")){
			System.out.println(Integer.toString(behaviour.attackRate()));
		}
		else if (command.equals("Defend")){
			System.out.println(Integer.toString(behaviour.defendRate()));
		}
		else{
			System.out.println("ERROR");
		}

	}

	public String getName() {

		System.out.println(name);
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBehavior(String robotType) {
		//todo
		if (robotType.equals("normal")){
//			robot = new normalRobot();
			System.out.println("I AM NORMAL");
			behaviour = new normalRobot();
		}
		else if (robotType.equals("aggressive")){
			behaviour = new angryRobot();
				System.out.println("THIS... IS.... SPARTAAAAAAAAA");
		}
		else if (robotType.equals("defensive")){
			behaviour = new defensiveRobot();
			System.out.println("MERCY B");
		}
		else{
			System.out.println("Invalid input!");
		}
//		return null;

	}
}

interface IBehaviour {
	public int moveCommand();
	public int attackRate();
	public int defendRate();
}
class normalRobot  implements IBehaviour{



	public int moveCommand(){
		System.out.println("Normal Moved ");
		Random r = new Random();
		int i1 = r.nextInt(10 - 0) + 10;
		return i1 ;
	}
	public int attackRate(){
		System.out.println("Normal Attacking at.. ");
		Random r = new Random();
		int i1 = r.nextInt(30 - 0) + 30;
		return i1;

	}
	public int defendRate(){
		System.out.println("Normal Defending at ");
		Random r = new Random();
		int i1 = r.nextInt(30 - 0) + 30;
		return i1;

	}
}
class angryRobot implements IBehaviour{

	public int moveCommand(){
		System.out.println("Angry Moving...");
		Random r = new Random();
		int i1 = r.nextInt(40 - 0) + 40;
		return i1;
	}
	public int attackRate(){
		System.out.println("Angry Attacked...");
		Random r = new Random();
		int i1 = r.nextInt(50 - 0) + 50;
		return i1;

	}
	public int defendRate(){
		System.out.println("Defended...");
		Random r = new Random();
		int i1 = r.nextInt(5 - 0) + 5;
		return i1;
	}

}
class defensiveRobot implements IBehaviour{

	public int moveCommand(){
		System.out.println("Scared Moving...");
		Random r = new Random();
		int i1 = r.nextInt(2 - 0) + 2;
		return i1;
	}
	public int attackRate(){
		System.out.println("Scared Attacked...");
		Random r = new Random();
		int i1 = r.nextInt(5 - 0) + 5;
		return i1;

	}
	public int defendRate(){
		System.out.println("Scared Defending...");
		Random r = new Random();
		int i1 = r.nextInt(40 - 0) + 40;
		return i1;

	}

}
public class RobotGame {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior("normal");
		r2.setBehavior("aggressive");
		r3.setBehavior("defensive");


		r1.getName();


		
		r1.behave("Move");
		r2.behave("Attack");
		r3.behave("Defend");

//		//change the behaviors of each robot.
		r1.setBehavior("defensive");
		r2.setBehavior("normal");
		r3.setBehavior("aggressive");
//
//		r1.behave();
//		r2.behave();
//		r3.behave();
	}
}