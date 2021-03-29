package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: HasQueuedThread
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 17:00
 */

class MyService4 {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class HasQueuedThread {
    public static void main(String[] args) throws InterruptedException {
        MyService4 service = new MyService4();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        });
        t1.start();

        Thread.sleep(500);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        });
        t2.start();

        Thread.sleep(500);
        System.out.println(service.lock.hasQueuedThread(t1));
        System.out.println(service.lock.hasQueuedThread(t2));
        System.out.println(service.lock.hasQueuedThreads());
    }
}
