package cn.shyshetxwh.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static junit.framework.Assert.assertEquals;

/**
 * FileName: TimerPutTakeTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 16:57
 */
public class TimerPutTakeTest extends PutTakeTest {
    private BarrierTimer timer = new BarrierTimer();

    public TimerPutTakeTest(int capacity, int nTrials, int nPairs) {
        super(capacity, nTrials, nPairs);
        barrier = new CyclicBarrier(2 * nPairs + 1, timer);
    }

    @Override
    void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                exec.execute(new Producer());
                exec.execute(new Consumer());
            }
            barrier.await();
            //TimeUnit.SECONDS.sleep(4);
            barrier.await();

            long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);
            System.out.print("Throughput: " + nsPerItem + " ns/item");
            assertEquals(putSum.get(), takeSum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            int tpt = 100000;
            for (int cap = 1; cap <= 1000; cap *= 10) {
                System.out.println("capacity: " + cap);
                for (int pairs = 1; pairs <= 128; pairs *= 2) {
                    TimerPutTakeTest t = new TimerPutTakeTest(cap, tpt, pairs);
                    System.out.print("Pairs: " + pairs + "\t");
                    t.test();
                    System.out.print("\t");
                    Thread.sleep(1000);
                    t.test();
                    System.out.println();
                    Thread.sleep(1000);
                }
            }
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
