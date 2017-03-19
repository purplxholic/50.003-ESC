package Week6;

import java.util.Random;

public class BufferFixed {
	public static void main (String[] args) throws Exception {
		Buffer buffer = new Buffer (10);
		MyProducer prod = new MyProducer(buffer);
		prod.start();
		MyConsumer cons = new MyConsumer(buffer);
		cons.start();
	}
}

class MyProducer extends Thread {
	private Buffer buffer;
	
	public MyProducer (Buffer buffer) {
		this.buffer = buffer;
	}
	
	public void run () {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		buffer.addItem(new Object());
	}
}

class MyConsumer extends Thread {
	private Buffer buffer;
	
	public MyConsumer (Buffer buffer) {
		this.buffer = buffer;
	}
	
	public void run () {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		buffer.removeItem();
	}
}

class Buffer {
	public int SIZE;	
	private Object[] objects;
	private int count = 0;

	public Buffer (int size) {
		SIZE = size;
		objects = new Object[SIZE];
	}
	
	
	//can make the method synchronized if you want the entire method sync! 
	public synchronized void addItem (Object object) throws InterruptedException {
		while (count == (SIZE -1)){
			wait();
		}
		
				objects[count] = object;
				count++;
				notifyAll(); 
		
			
	}
	
	public Object removeItem() throws InterruptedException {
		while (count ==0){
			wait();
		}
				Object removed = objects[count];
				count--;
				notifyAll();
				return removed;
	
	}
}