package cn.shyshetxwh.v4;

import java.util.concurrent.TimeUnit;

/**
 * FileName: Join
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/10 0010 9:21
 */
public class Join {
    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Domino(previous), String.valueOf(i));
            t.start();
            previous = t;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
}
