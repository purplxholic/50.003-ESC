package com.example;

import java.util.Scanner;

public class PizzaStore {
    public static void main(String[] args) {
        Pizza customersPizza = null;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Would you like a Cheese,Greek or Pepperoni Pizza?");
        if (userInput.hasNextLine()){
            String pizzaType = userInput.nextLine();
            PizzaStore cook = new PizzaStore();
            if (pizzaType!=null){
                cook.orderPizza(pizzaType);
            }
            else {
                System.out.print("Please enter your preferred pizza");
            }

        }
    }

	public Pizza orderPizza (String type) {
		Pizza pizza = null;
		
        PizzaFactory factory = new PizzaFactory();
        pizza = factory.bakingPizza(type);

        System.out.println("Preparing the pizza...");
        pizza.prepare();
        System.out.println("Baking your pizza...");
        pizza.bake();
        System.out.println("Cutting it into pieces...");
        pizza.cut();
        System.out.println("Boxing it up for you...");
        pizza.box();

        System.out.println("It's done! Please collect your pizza.");
        return pizza;
	}
}

class Pizza {

	public void prepare() {

	}

	public void box() {
	}

	public void cut() {
	}

	public void bake() {
	}
}

class CheesePizza extends Pizza {}
class GreekPizza extends Pizza {}
class PepperoniPizza extends Pizza {}

//mine
class PizzaFactory{
	public Pizza bakingPizza(String name){
		Pizza customerPizza = null;
        if (name.equals("cheese")) {
            System.out.println("You ordered Cheese Pizza");
             return  new CheesePizza();
        }
        if (name.equals("greek")) {
            System.out.println("You ordered Greek Pizza");
            return new GreekPizza();
        }
        if (name.equals("pepperoni")) {
            System.out.println("You ordered Pepperoni Pizza");
            return new PepperoniPizza();
        }
        return null;

	}
}

