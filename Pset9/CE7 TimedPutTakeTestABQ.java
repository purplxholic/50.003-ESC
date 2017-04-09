package Week11;

import java.util.concurrent.*;
//already testing boundedbuffer from puttaketest 
//producer and consumer class recreate them, maybe change the methods used inside 
class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }
}

public class TimedPutTakeTestABQ extends PutTakeTestABQ {
    private BarrierTimer timer = new BarrierTimer();

    public TimedPutTakeTestABQ(int cap, int pairs, int trials) {
        super(cap, pairs, trials);
        barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }

    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new PutTakeTestABQ.Producer());
                pool.execute(new PutTakeTestABQ.Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime() / (nPairs * (long) nTrials); //nPairs is no of producers and consumers
            //nsPerItem: each item per operation 
            System.out.print("Throughput: " + nsPerItem + " ns/item");
            assert(putSum.get() == takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        int tpt = 100000; // trials per thread
        for (int cap = 1; cap <= 1000; cap *= 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) { //pairs is how many pairs of threads 
                TimedPutTakeTestABQ t = new TimedPutTakeTestABQ(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
                Thread.sleep(1000);
                t.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        PutTakeTest.pool.shutdown();
    }
}
   