package cn.shyshetxwh.v4.v41.fairLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: FairTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 15:56
 */

class MyService {
    public Lock lock;

    public MyService(boolean fair) {
        this.lock = new ReentrantLock(fair);
    }

    public void testMethod() {
        try {
            lock.lock();
            System.out.println("testMethod " + Thread.currentThread().getName());
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class FairTest {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService(false);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.testMethod();
                }
            }, "array1 " + (i + 1)).start();
        }

        Thread.sleep(500);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.testMethod();
                }
            }, "array2 " + (i + 1)).start();
        }
    }
}
