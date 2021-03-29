package cn.shyshetxwh.v4.v41.conditionTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: ConditionTestMoreMethod
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 8:18
 */

class MyService {
    private Lock lock = new ReentrantLock();

    public void methodA() {
        try {
            lock.lock();
            System.out.println("methodA begin ThreadName=" + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("methodA end ThreadName=" + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        try {
            lock.lock();
            System.out.println("methodB begin ThreadName=" + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("methodB end ThreadName=" + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionTestMoreMethod {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.methodA();
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.methodA();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.methodB();
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.methodB();
            }
        }, "BB").start();
    }
}
