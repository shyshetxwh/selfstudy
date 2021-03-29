package cn.shyshetxwh.examples;

import java.util.concurrent.TimeUnit;

/**
 * FileName: NoVisibility
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 10:20
 */
public class NoVisibility {
    private /*volatile*/ static boolean ready;
    private /*volatile*/ static int num;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            System.out.println(ready);
            while (!ready)
                Thread.yield();
            System.out.println("num = " + num);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        TimeUnit.SECONDS.sleep(5);
        num = 42;
        ready = true;
    }
}
