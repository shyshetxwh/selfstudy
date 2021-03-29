package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: LockInterruttiblyTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 17:44
 */

class MyService6 {
    private ReentrantLock lock = new ReentrantLock();

    public void testMethod() {
        try {
            lock.lockInterruptibly();
            System.out.println("begin " + Thread.currentThread().getName() + "  " + System.currentTimeMillis());
            for (int i = 0; i < 10000000; i++) {
                String s = new String();
                Math.random();
                Thread.yield();
            }
            System.out.println("end " + Thread.currentThread().getName() + "  " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

public class LockInterruttiblyTest {
    public static void main(String[] args) throws InterruptedException {
        MyService6 service = new MyService6();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.testMethod();
            }
        }, "A").start();

        Thread.sleep(500);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                service.testMethod();
            }
        }, "B");
        t.start();

        Thread.sleep(500);
        t.interrupt();

        System.out.println("main interrupt B");
    }
}
