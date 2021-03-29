package cn.shyshetxwh.examples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * FileName: AtomicPseudoRandom
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/17 0017 8:59
 */
public class AtomicPseudoRandom extends PseudoRandom {
    private AtomicInteger seed;

    public AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    @Override
    public int nextInt(int n) {
        while (true) {
            int s = seed.get();
            int nextSeed = calculateNext(s);
            if (seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ReentrantLockPseudoRandom random1 = new ReentrantLockPseudoRandom(100000);
        AtomicPseudoRandom random2 = new AtomicPseudoRandom(100000);
        test(random1);
        test(random2);
    }

    private static void test(PseudoRandom random) throws BrokenBarrierException, InterruptedException {
        for (int i = 1; i <= 64; i *= 2) {
            int n = i;
            System.out.print("Thread Number: " + n + "  \t");
            CyclicBarrier barrier = new CyclicBarrier(n + 1);
            long start = System.nanoTime();
            for (int j = 0; j < n; j++) {
                Thread t = new Thread(() -> {
                    try {
                        barrier.await();
                        for (int k = 0; k < 100000; k++) {
                            int number = random.nextInt(k + 1);
                        }
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                });
                t.start();
            }
            barrier.await();
            barrier.await();
            long end = System.nanoTime();
            double per = (end - start) / (n * 100000);
            System.out.println("throughput: " + per);
        }
    }
}
