package cn.shyshetxwh.v4.v41.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: PrintTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 20:08
 */

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private volatile int next = 1;

    public void testMethod1() {
        try {
            lock.lock();
            while (next != 1)
                condition.await();
            System.out.println("AAA");
            next = 2;
            condition.signalAll();
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
            while (next != 2)
                condition.await();
            System.out.println("    BBB");
            next = 3;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public void testMethod3() {
        try {
            lock.lock();
            while (next != 3)
                condition.await();
            System.out.println("        CCC");
            next = 1;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

}

public class PrintTest {
    public static void main(String[] args) {
        MyService service = new MyService();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.testMethod1();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.testMethod2();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.testMethod3();
                }
            }).start();
        }
    }
}
