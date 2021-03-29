package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: GetWaitQueueTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 16:47
 */

class MyService3 {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {
            lock.lock();
            System.out.println(lock.hasWaiters(condition) ? ("has " + lock.getWaitQueueLength(condition) + " wait condition") : ("no"));
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


}

public class GetWaitQueueTest {
    public static void main(String[] args) throws InterruptedException {
        MyService3 service = new MyService3();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.waitMethod();
                }
            }).start();
        }

        Thread.sleep(2000);
        service.notifyMethod();
        /*while (service.lock.getWaitQueueLength(service.condition) > 0) {
        }*/
    }
}
