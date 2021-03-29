package cn.shyshetxwh.v3.v33.separate;

import java.util.Date;

/**
 * FileName: ThreadLocalInitialSeparateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 22:40
 */

class ThreadLocalExt2 extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}

class Tools2 {
    public static ThreadLocalExt2 ext2 = new ThreadLocalExt2();
}

class Thread3 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("In Thread3 get:" + Tools2.ext2.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadLocalInitialSeparateTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("    In main get:" + Tools2.ext2.get());
            Thread.sleep(100);
        }
        Thread.sleep(5000);
        new Thread3().start();
    }
}
