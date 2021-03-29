package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread9;

/**
 * FileName: ThreadTest9
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 21:18
 */
public class ThreadTest9 {
    public static void main(String[] args) throws InterruptedException {
        MyThread9 myThread9 = new MyThread9();
        myThread9.start();
        Thread.sleep(2000);
        myThread9.interrupt();
        System.out.println("zzzzzzzzzz");

    }
}
