package cn.shyshetxwh.v3.v31;

/**
 * FileName: NotifyOne
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 17:08
 */

class MyService2 {
    private Object lock = new Object();

    public void waitMethod() {
        try {
            synchronized (lock) {
                System.out.println("begin wait " + System.currentTimeMillis()
                        + "  " + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait " + System.currentTimeMillis()
                        + "  " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyMethod() {
        synchronized (lock) {
            System.out.println("begin notify " + System.currentTimeMillis()
                    + "  " + Thread.currentThread().getName());
            lock.notify();
            System.out.println("end notify " + System.currentTimeMillis()
                    + "  " + Thread.currentThread().getName());
        }
    }
}

class Thread7 extends Thread {
    private MyService2 service;

    public Thread7(MyService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethod();
    }
}

class Thread8 extends Thread {
    private MyService2 service;

    public Thread8(MyService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.notifyMethod();
    }
}

public class NotifyOne {
    public static void main(String[] args) throws InterruptedException {
        MyService2 service = new MyService2();

        for (int i = 0; i < 10; i++) {
            Thread7 t1 = new Thread7(service);
            t1.start();
        }

        Thread.sleep(1000);

        Thread8 t2;
        for (int i = 0; i < 5; i++) {
            t2 = new Thread8(service);
            t2.start();
            Thread.sleep(500);
        }
    }
}
