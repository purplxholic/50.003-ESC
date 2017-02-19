package Week4;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class ArenaZero {
	private int ID =0;
	private String userName;
	private Hashtable<String,Integer> coordinates; 
	public int playerHealth=0; 
	public String currentWeapon; 
	private ArrayList<Weapon> weapons; 
	public ArenaZero(){
		System.out.println("You ID will be" + ID);
		
		ID++;
		playerHealth = 100; 
		initialiseWeapon();
	}
	
	public ArenaZero(String name){
		this.userName = name;
		System.out.println("Welcome player " + userName);
		coordinates = new Hashtable<String, Integer>();
		coordinates.put("X", 0);
		coordinates.put("Y", 0);
		playerHealth = 100; 
		initialiseWeapon();
		
	}


	public void playerMovement(String direction){
		if (direction.equals("left")){
			int oldX = coordinates.get("X");
			coordinates.put("X", new Integer(oldX-5));
		}
		else if (direction.equals("right")){
			int oldX = coordinates.get("X");
			coordinates.put("X", new Integer(oldX+5));

		}
		else if (direction.equals("up")){
			int oldY = coordinates.get("Y");
			coordinates.put("Y", new Integer(oldY+5));

		}
		else if (direction.equals("down")){
			int oldY = coordinates.get("Y");
			coordinates.put("Y", new Integer(oldY-5));
		}
		else{
			System.out.println("Input error!");
		}
		for (String string:coordinates.keySet()){
			System.out.println(string + " position: "+coordinates.get(string));
		}

	}
	
	public void initialiseWeapon(){
		weapons = new ArrayList<>();
		Sword sword = new Sword();
		Gun gun = new Gun();
		weapons.add(sword);
		weapons.add(gun); 
	}
	public void getWeapon(){
		System.out.println("A weapon box has spawned! It is a...\n");
		Random random = new Random(); 
		int index = random.nextInt(weapons.size());
		System.out.println(weapons.get(index));
		setPlayerHealth(weapons.get(index).setDamage());
		
		
	}
	
	private void setPlayerHealth(int damage){
		this.playerHealth += damage;
		getplayerHealth(); //to update the player on his health
	}

	public void getplayerHealth(){
		System.out.println("Your current health is: " + playerHealth);
		
	}
	public String getName(){
		return userName;
	}
	
	public int getID(){
		return ID; 
	}
	
	public int getX(){
		return coordinates.get("X");
	}
	
	public int getY(){
		return coordinates.get("Y"); 
	}
}

interface Weapon{
	public String weaponName="";
	public int damage=0; 
	public String getName();
	public int setDamage(); 
	public String toString();
}

class Sword implements Weapon{
	public String weaponName="Sword";
	public int damage = -10; 
	public String getName(){
		return weaponName; 
	}
	public int setDamage(){
		return damage; 
	}
	@Override
	public String toString(){
		return "Sword";
	}
}
class Gun implements Weapon{
	public String weaponName="Gun";
	public int damage = -30; 
	public String getName(){
		return weaponName; 
		
	}
	public int setDamage(){
		return damage;
	}
	@Override
	public String toString(){
		return "Gun";
	}
	
}
