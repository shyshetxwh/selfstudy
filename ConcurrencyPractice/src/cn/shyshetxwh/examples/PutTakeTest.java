package cn.shyshetxwh.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.Assert.assertEquals;

/**
 * FileName: PutTakeTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 10:53
 */
public class PutTakeTest {
    protected static final ExecutorService exec = Executors.newCachedThreadPool();
    protected final AtomicInteger putSum = new AtomicInteger(0);
    protected final AtomicInteger takeSum = new AtomicInteger(0);
    protected CyclicBarrier barrier;
    protected final BoundedBuffer<Integer> bb;
    protected final int nTrials, nPairs;


    public PutTakeTest(int capacity, int nTrials, int nPairs) {
        this.nTrials = nTrials;
        this.nPairs = nPairs;
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                exec.execute(new Producer());
                exec.execute(new Consumer());
            }
            barrier.await();
            //TimeUnit.SECONDS.sleep(15);
            barrier.await();
            //System.out.println("all end");
            assertEquals(putSum.get(), takeSum.get());
            System.out.println(putSum.get());
            System.out.println(takeSum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                int seed = this.hashCode() ^ (int) System.nanoTime();
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                //System.out.println("put " + sum);
                putSum.getAndAdd(sum);
                barrier.await();
                //System.out.println("put over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                //System.out.println("tak " + sum);
                takeSum.getAndAdd(sum);
                barrier.await();
                //System.out.println("tak over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    public static void main(String[] args) {
        new PutTakeTest(10, 100000, 10).test();
        exec.shutdown();
    }
}
