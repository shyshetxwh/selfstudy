package cn.shyshetxwh.v3.v32.JoinAndInterrupt;

/**
 * FileName: JoinException
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 18:49
 */

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String s = new String();
            Math.random();
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        try {
            Thread1 t1 = new Thread1();
            t1.start();
            t1.join();
            System.out.println("t2 run end");
        } catch (InterruptedException e) {
            System.out.println("t2 catch");
            e.printStackTrace();
        }
    }
}

class Thread3 extends Thread {
    private Thread2 t2;

    public Thread3(Thread2 t2) {
        this.t2 = t2;
    }

    @Override
    public void run() {
        t2.interrupt();
    }
}

public class JoinException {
    public static void main(String[] args) throws InterruptedException {
        Thread2 t2 = new Thread2();
        t2.start();

        Thread.sleep(500);

        Thread3 t3 = new Thread3(t2);
        t3.start();
    }
}
