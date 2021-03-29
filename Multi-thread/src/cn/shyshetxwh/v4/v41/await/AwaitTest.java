package cn.shyshetxwh.v4.v41.await;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: AwaitTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 19:38
 */

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void testMethod() {
        try {
            lock.lock();
            System.out.println("await begin " + System.currentTimeMillis());
            condition.await(3, TimeUnit.SECONDS);
            System.out.println("await   end " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public void testMethod2() {
        try {
            lock.lock();
            System.out.println("await begin " + System.currentTimeMillis());
            condition.awaitNanos(5000000000L);
            System.out.println("await   end " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }


}

public class AwaitTest {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.testMethod();
            }
        }).start();

        Thread.sleep(4000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.testMethod2();
            }
        }).start();
    }
}
