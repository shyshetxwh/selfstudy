package cn.shyshetxwh.v3.v31;

/**
 * FileName: NotifyOne
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 17:08
 */

class MyService3 {
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
            System.out.println("begin notifyAll " + System.currentTimeMillis()
                    + "  " + Thread.currentThread().getName());
            lock.notifyAll();
            System.out.println("end notifyAll " + System.currentTimeMillis()
                    + "  " + Thread.currentThread().getName());
        }
    }
}

class Thread9 extends Thread {
    private MyService3 service;

    public Thread9(MyService3 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethod();
    }
}

class Thread10 extends Thread {
    private MyService3 service;

    public Thread10(MyService3 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.notifyMethod();
    }
}

public class NotifyAll {
    public static void main(String[] args) throws InterruptedException {
        MyService3 service = new MyService3();

        for (int i = 0; i < 10; i++) {
            new Thread9(service).start();
        }

        Thread.sleep(1000);

        new Thread10(service).start();
    }
}
