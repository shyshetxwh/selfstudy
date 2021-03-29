package cn.shyshetxwh.v4.v41.tryLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: TryLockTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 19:22
 */

class MyService {
    private ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        if (lock.tryLock())
            System.out.println(Thread.currentThread().getName() + " get lock");
        else
            System.out.println(Thread.currentThread().getName() + " not get lock");
    }

}


public class TryLockTest {
    public static void main(String[] args) {
        MyService service = new MyService();

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
