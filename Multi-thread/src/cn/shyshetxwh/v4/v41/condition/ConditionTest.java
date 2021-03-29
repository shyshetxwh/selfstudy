package cn.shyshetxwh.v4.v41.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: ConditionTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 8:33
 */

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            System.out.println("A");
            condition.await();
            System.out.println("B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("release lock");
        }
    }
}

public class ConditionTest {
    public static void main(String[] args) {
        MyService service = new MyService();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        }).start();
    }
}
