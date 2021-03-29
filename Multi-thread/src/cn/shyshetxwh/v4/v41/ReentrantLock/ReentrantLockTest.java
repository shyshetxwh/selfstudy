package cn.shyshetxwh.v4.v41.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: ReentrantLockTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 8:10
 */

class MyService {
    private Lock lock = new ReentrantLock();

    public void testMethod() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " "
                    + (i + 1));
        }
        lock.unlock();
    }
}

public class ReentrantLockTest {
    public static void main(String[] args) {
        MyService service = new MyService();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.testMethod();
                }
            }).start();
        }
    }
}
