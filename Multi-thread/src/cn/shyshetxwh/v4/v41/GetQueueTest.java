package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: GetQueueTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 16:37
 */

class MyService2 {
    public ReentrantLock lock = new ReentrantLock();

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " enter method");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class GetQueueTest {
    public static void main(String[] args) throws InterruptedException {
        MyService2 service = new MyService2();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.serviceMethod();
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println("Has " + service.lock.getQueueLength() + " wait the lock");
    }
}
