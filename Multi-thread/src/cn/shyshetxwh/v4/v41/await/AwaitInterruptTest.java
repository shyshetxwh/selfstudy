package cn.shyshetxwh.v4.v41.await;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: AwaitInterruptTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 20:00
 */

class MyService3 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void testMethod() {
        try {
            lock.lock();
            System.out.println("wait begin");
            condition.awaitUninterruptibly();
            System.out.println("wait   end");
        } finally {
            lock.unlock();
        }
    }

}

public class AwaitInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        MyService3 service = new MyService3();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                service.testMethod();
            }
        });
        t.start();

        Thread.sleep(3000);
        t.interrupt();
    }
}
