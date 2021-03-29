package cn.shyshetxwh.v3.v31;

/**
 * FileName: WaitTimeBackLock
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 19:25
 */

class MyService4 {
    public void testMethod() {
        try {
            synchronized (this) {
                System.out.println("wait begin " + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
                wait(5000);
                System.out.println("wait end " + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void longTimeSyn() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread11 extends Thread {
    private MyService4 service;

    public Thread11(MyService4 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class Thread12 extends Thread {
    private MyService4 service;

    public Thread12(MyService4 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.longTimeSyn();
    }
}

public class WaitTimeBackLock {
    public static void main(String[] args) {
        MyService4 service = new MyService4();

        Thread11[] threads = new Thread11[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread11(service);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        new Thread12(service).start();
    }
}
