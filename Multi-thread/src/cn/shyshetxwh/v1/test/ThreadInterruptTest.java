package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread10;

/**
 * FileName: ThreadInterruptTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 21:23
 */
public class ThreadInterruptTest {
    public static void main(String[] args) {
        try {
            MyThread10 myThread10 = new MyThread10();
            myThread10.start();
            Thread.sleep(1000);
            myThread10.interrupt();
            System.out.println("是否停止1？=" + myThread10.interrupted());
            System.out.println("是否停止2？=" + myThread10.interrupted());
        } catch (InterruptedException e) {
            System.out.println("main match");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
