package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread10;

/**
 * FileName: ThreadInterruptTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 21:49
 */
public class ThreadInterruptTest2 {
    public static void main(String[] args) {
        try {
            MyThread10 thread = new MyThread10();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("是否停止1？=" + thread.isInterrupted());
            System.out.println("是否停止2？=" + thread.isInterrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
