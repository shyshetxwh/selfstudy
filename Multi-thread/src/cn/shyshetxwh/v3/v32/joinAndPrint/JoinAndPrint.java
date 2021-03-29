package cn.shyshetxwh.v3.v32.joinAndPrint;

/**
 * FileName: JoinAndPrint
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 20:28
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
                System.out.println("begin t1 ThreadName="
                        + Thread.currentThread().getName() + "    "
                        + System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("end t1 ThreadName="
                        + Thread.currentThread().getName() + "    "
                        + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 extends Thread {
    @Override
    public synchronized void run() {
        try {
            System.out.println("begin t2 ThreadName="
                    + Thread.currentThread().getName() + "    "
                    + System.currentTimeMillis());
            Thread.sleep(500);
            System.out.println("end t2 ThreadName="
                    + Thread.currentThread().getName() + "    "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JoinAndPrint {
    public static void main(String[] args) throws InterruptedException {
        Thread2 t2 = new Thread2();
        Thread1 t1 = new Thread1(t2);

        t1.start();
        t2.start();
        t2.join(200);
        System.out.println("main end" + System.currentTimeMillis());
    }
}
