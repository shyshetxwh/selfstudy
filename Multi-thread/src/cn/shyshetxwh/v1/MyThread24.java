package cn.shyshetxwh.v1;

import java.util.Random;

/**
 * FileName: MyThread24
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 10:19
 */
public class MyThread24 extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long addResult = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 50; j++) {
                Random rand = new Random();
                rand.nextInt();
                addResult += j;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("thread24 use time=" + (end - start));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            MyThread24 thread1 = new MyThread24();
            thread1.setPriority(10);
            thread1.start();

            MyThread23 thread2 = new MyThread23();
            thread2.setPriority(1);
            thread2.start();
        }
    }
}
