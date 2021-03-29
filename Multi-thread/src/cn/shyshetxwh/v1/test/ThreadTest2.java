package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread2;

/**
 * FileName: ThreadTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:53
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        myThread2.setName("MyThread");
        myThread2.start();

        for (int i = 0; i < 10000; i++) {
            System.out.println("main= "+Thread.currentThread().getName());
        }
    }
}
