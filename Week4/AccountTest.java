package Week4;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest { 
   @Test
   public void Compare () { 
	   Account a = new Account(); 
	   Account b = new Account(); 
	   b.compare(a); 
	   //test case will see here if there are any exceptions. if there is, there is a bug 
   }

   @Test
   public void testCalAmount() {
	   Account a = new Account(); 
	   a.setBalance(10);
	   int amount = a.calAmount();
	   assertTrue(amount == 30); // what is the expected after running the test case? 3- here 
   }
   
   @Test
   public void testSetupAccount() {
	   Account a = new Account(); 
   }
   
   @Test
   public void testWithDraw() {
	   Account a = new Account(); 
	   a.setBalance(100);
	   boolean success = a.withdraw(1000); //withdraw. if it's too big, it's impossible 
	   assertFalse(success);
   }
}
