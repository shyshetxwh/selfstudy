package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread7;

/**
 * FileName: ThreadTest7
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:25
 */
public class ThreadTest7 {
    public static void main(String[] args) throws InterruptedException {
        MyThread7 myThread7 = new MyThread7();
        System.out.println("begin ==" + myThread7.isAlive());
        myThread7.start();
        Thread.sleep(1000);
        System.out.println("end ==" + myThread7.isAlive());
    }
}
