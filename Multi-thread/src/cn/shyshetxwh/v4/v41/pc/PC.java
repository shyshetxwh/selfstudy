package cn.shyshetxwh.v4.v41.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: PC
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 15:46
 */

class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void produce() {
        try {
            lock.lock();
            while (hasValue) {
                System.out.println("await ★★");
                condition.await();
            }
            System.out.println("produce ★");
            hasValue = true;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        try {
            lock.lock();
            while (!hasValue) {
                System.out.println("await ☆☆");
                condition.await();
            }
            System.out.println("consume ☆");
            hasValue = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
        }
    }


}

public class PC {
    public static void main(String[] args) {
        MyService service = new MyService();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        service.produce();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        service.consume();
                }
            }).start();
        }
    }
}
