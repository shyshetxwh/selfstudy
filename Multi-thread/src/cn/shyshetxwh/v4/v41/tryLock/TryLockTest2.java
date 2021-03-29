package cn.shyshetxwh.v4.v41.tryLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: TryLockTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 19:29
 */

class MyService2 {
    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println("        " + Thread.currentThread().getName()
                        + " get lock time=" + System.currentTimeMillis());
                Thread.sleep(10000);
            } else
                System.out.println("        " + Thread.currentThread().getName()
                        + " not get lock time=");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }

    }

}

public class TryLockTest2 {
    public static void main(String[] args) {
        MyService2 service = new MyService2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        }, "B").start();
    }
}
