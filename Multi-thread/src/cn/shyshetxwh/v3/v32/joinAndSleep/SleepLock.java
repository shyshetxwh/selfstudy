package cn.shyshetxwh.v3.v32.joinAndSleep;


/**
 * FileName: SleepLock
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 19:47
 */

class Thread1 extends Thread {
    private Thread2 t2;

    public Thread1(Thread2 t2) {
        this.t2 = t2;
    }

    @Override
    public void run() {
        try {
            synchronized (t2) {
                t2.start();
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("t2 run begin:" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("t2 run   end:" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void bService() {
        System.out.println("t2 print:" + System.currentTimeMillis());
    }
}

class Thread3 extends Thread {
    private Thread2 t2;

    public Thread3(Thread2 t2) {
        this.t2 = t2;
    }

    @Override
    public void run() {
        t2.bService();
    }
}

class Thread4 extends Thread {
    private Thread2 t2;

    public Thread4(Thread2 t2) {
        this.t2 = t2;
    }

    @Override
    public void run() {
        try {
            synchronized (t2) {
                t2.start();
                t2.join();
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    String s = new String();
                    Math.random();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SleepLock {
    public static void main(String[] args) throws InterruptedException {
        Thread2 t2 = new Thread2();

//        new Thread1(t2).start();
//        Thread.sleep(1000);
//        new Thread3(t2).start();

        new Thread4(t2).start();
        Thread.sleep(1000);
        new Thread3(t2).start();
    }
}
