package cn.shyshetxwh.v4.v41.moreCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: MoreCondition
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 15:27
 */

class MyService {
    private Lock lock = new ReentrantLock();
    public Condition condition1 = lock.newCondition();
    public Condition condition2 = lock.newCondition();

    public void await1() {
        try {
            lock.lock();
            System.out.println("begin await1 time=" + System.currentTimeMillis()
                    + "ThreadName=" + Thread.currentThread().getName());
            condition1.await();
            System.out.println("end await1 time=" + System.currentTimeMillis()
                    + "ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void await2() {
        try {
            lock.lock();
            System.out.println("begin await2 time=" + System.currentTimeMillis()
                    + "ThreadName=" + Thread.currentThread().getName());
            condition2.await();
            System.out.println("end await2 time=" + System.currentTimeMillis()
                    + "ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll1() {
        try {
            lock.lock();
            System.out.println("signalAll1 time=" + System.currentTimeMillis()
                    + "ThreadName=" + Thread.currentThread().getName());
            condition1.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll2() {
        try {
            lock.lock();
            System.out.println("signalAll2 time=" + System.currentTimeMillis()
                    + "ThreadName=" + Thread.currentThread().getName());
            condition2.signalAll();
        } finally {
            lock.unlock();
        }
    }


}

public class MoreCondition {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.await1();
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.await2();
            }
        }, "B").start();

        Thread.sleep(3000);
        service.signalAll1();
    }
}
