package cn.shyshetxwh.v4.v42;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * FileName: ReentrantReadWriteTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 20:36
 */

class MyService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("get read lock " + Thread.currentThread().getName()
                        + "  " + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("get write lock " + Thread.currentThread().getName()
                        + "  " + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

public class ReentrantReadWriteTest {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }, "A").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }, "B").start();

        Thread.sleep(20000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }, "C").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }, "D").start();

        Thread.sleep(20000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }, "E").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }, "F").start();

        Thread.sleep(20000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }, "G").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }, "H").start();
    }
}
