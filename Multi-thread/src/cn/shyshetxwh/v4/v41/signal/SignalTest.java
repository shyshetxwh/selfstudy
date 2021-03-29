package cn.shyshetxwh.v4.v41.signal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: SingnalTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 8:52
 */

class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println("await time=" + System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal time=" + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class SignalTest {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.await();
            }
        }).start();

        Thread.sleep(3000);
        service.signal();
    }
}
