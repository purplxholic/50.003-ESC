package Week11;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class BoundedBufferTestCE5P1 {	
	private static final long LOCKUP_DETECT_TIMEOUT = 1000;
	
	@Test
	public void testIsEmptyWhenConstructued () {
		BoundedBufferWithSpec<Integer> bb = new BoundedBufferWithSpec<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	//tests 10 threads, afterwards buffer will have 10 threads 
	@Test
	public void testIsFullAfterPuts () throws InterruptedException {
		final BoundedBufferWithSpec<Integer> bb = new BoundedBufferWithSpec<Integer>(10);
		
		Runnable task = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};

		Thread[] threads = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread (task);
			threads[i].start();
		}

		for (int i = 0; i < 10; i++) {
			threads[i].join();
		}

		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
	
	@Test
	public void testTakeBlocksWhenEmpty () {
		final BoundedBufferWithSpec<Integer> bb = new BoundedBufferWithSpec<Integer>(10);
		Thread taker = new Thread() {
			public void run() {
				try {
					int unused = bb.take();
					assertTrue(false);
				} catch (InterruptedException success) {} //if interrupted, the exception is caught here
			}
		};
		
		try {
			taker.start();
			Thread.sleep(LOCKUP_DETECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DETECT_TIMEOUT);
			assertFalse(taker.isAlive()); //the taker should not be alive for some time
		} catch (Exception unexpected) {
			assertTrue(false);
		}
	}
	//CE5: create buffer, put in all and take out all
	@Test
	public void testPutandTake() throws InterruptedException{
		final BoundedBufferWithSpec<Integer> bb = new BoundedBufferWithSpec<Integer>(10);
		Runnable put = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};
		Runnable take = new Runnable () {
			public void run() {
				try {
					bb.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};
		Thread[] putters = new Thread[10];
		Thread[] takers = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			putters[i] = new Thread (put);
			putters[i].start();
		}

		for (int i = 0; i < 10; i++) {
			putters[i].join();
		}
		
		for (int i = 0; i < 10; i++) {
			takers[i] = new Thread (take);
			takers[i].start();
		}

		for (int i = 0; i < 10; i++) {
			takers[i].join();
		}
		
		assertFalse(bb.isFull());
		assertTrue(bb.isEmpty());
		
	}
	//CE5: test put and take position 
	@Test
	public void testPosition() throws InterruptedException{
		final BoundedBufferWithSpec<Integer> bb = new BoundedBufferWithSpec<Integer>(10);
		Runnable put = new Runnable () {
			public void run() {
				try {
					bb.put((new Random()).nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};
		
		Thread[] threads = new Thread[5];
		
		for (int i = 0; i < 5; i++) {
			threads[i] = new Thread (put);
			threads[i].start();
		}

		for (int i = 0; i < 5; i++) {
			threads[i].join();
		}
		
		assertFalse(bb.isEmpty());
		assertFalse(bb.isFull());
	}
}
