package cn.shyshetxwh.v2.v22;

/**
 * FileName: LockUpdateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 16:33
 */

class MyService {
    private String lock = "123";

    public void testMethod() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());
                lock = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread7 extends Thread {
    private MyService service;

    public Thread7(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class Thread8 extends Thread {
    private MyService service;

    public Thread8(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

public class LockUpdateTest {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        Thread7 t1 = new Thread7(service);
        t1.setName("A");

        Thread8 t2 = new Thread8(service);
        t2.setName("B");

        t1.start();
        //Thread.sleep(50);
        t2.start();
    }
}
