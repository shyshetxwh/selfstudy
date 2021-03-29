package cn.shyshetxwh.v4.v41.await;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: AwaitUntilTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 19:50
 */

class MyService2 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            Calendar ref = Calendar.getInstance();
            ref.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("await begin " + System.currentTimeMillis());
            condition.awaitUntil(ref.getTime());
            System.out.println("await   end " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }

    }

    public void notifyMethod() {
        try {
            Calendar ref = Calendar.getInstance();
            ref.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("notify begin " + System.currentTimeMillis());
            condition.signalAll();
            System.out.println("notify   end " + System.currentTimeMillis());
        } finally {
            lock.unlock();
        }
    }


}

public class AwaitUntilTest {
    public static void main(String[] args) throws InterruptedException {
        MyService2 service = new MyService2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.notifyMethod();
            }
        }).start();
    }
}
