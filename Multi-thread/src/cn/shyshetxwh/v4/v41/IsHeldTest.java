package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: IsHeldTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 17:38
 */

class MyService5 {
    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod() {
        try {
            System.out.println(lock.isHeldByCurrentThread());
            System.out.println(lock.isLocked());
            lock.lock();
            System.out.println(lock.isHeldByCurrentThread());
            System.out.println(lock.isLocked());
        } finally {
            lock.unlock();
            System.out.println(lock.isHeldByCurrentThread());
            System.out.println(lock.isLocked());
        }
    }

}

public class IsHeldTest {
    public static void main(String[] args) {
        MyService5 myService5 = new MyService5();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService5.serviceMethod();
            }
        }).start();
    }
}
