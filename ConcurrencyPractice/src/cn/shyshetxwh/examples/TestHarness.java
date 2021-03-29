package cn.shyshetxwh.examples;

import java.util.concurrent.CountDownLatch;

/**
 * FileName: TestHarness
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 8:57
 */
public class TestHarness {
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " ready!");
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();

        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        System.out.println("all task end");
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        long time = timeTasks(10, () -> {
            for (int i = 0; i < 1000000; i++) {
                i++;
            }
        });
        System.out.println(time);
    }
}
