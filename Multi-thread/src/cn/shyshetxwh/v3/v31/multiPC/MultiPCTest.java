package cn.shyshetxwh.v3.v31.multiPC;

/**
 * FileName: MultiPCTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 22:20
 */

class ValueObject {
    public static String value = "";
}

class P {
    private String lock;

    public P(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObject.value.equals("")) {
                    System.out.println("Producer " + Thread.currentThread().getName() + " WAITING ★");
                    lock.wait();
                }
                System.out.println("Producer " + Thread.currentThread().getName() + " RUNNING ☆");
                String value = System.currentTimeMillis() + "__" + System.nanoTime();
                ValueObject.value = value;
                lock.notify();
//                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class C {
    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println("Consumer " + Thread.currentThread().getName() + " WAITING ★");
                    lock.wait();
                }
                System.out.println("Consumer " + Thread.currentThread().getName() + " RUNNING ☆");
                ValueObject.value = "";
                lock.notify();
//                lock.notifyAll();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadP extends Thread {
    private P p;

    public ThreadP(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true)
            p.setValue();
    }
}

class ThreadC extends Thread {
    private C c;

    public ThreadC(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true)
            c.getValue();
    }
}

public class MultiPCTest {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);

        ThreadP[] threadPS = new ThreadP[2];
        ThreadC[] threadCS = new ThreadC[2];

        for (int i = 0; i < 2; i++) {
            threadPS[i] = new ThreadP(p);
            threadPS[i].setName("Producer" + (i + 1));
            ;

            threadCS[i] = new ThreadC(c);
            threadCS[i].setName("Consumer" + (i + 1));

            threadPS[i].start();
            threadCS[i].start();
            //Thread.sleep(1000);
        }

        Thread.sleep(5000);

        Thread[] array = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(array);

        for (Thread thread : array) {
            System.out.println(thread.getName() + " " + thread.getState());
        }
    }
}
