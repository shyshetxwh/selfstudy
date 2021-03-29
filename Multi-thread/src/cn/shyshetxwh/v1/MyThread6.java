package cn.shyshetxwh.v1;

/**
 * FileName: MyThread6
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:00
 */
public class MyThread6 extends Thread {
    private int i = 5;

    @Override
    public void run() {
        System.out.println("i=" + (i--) + " threadName=" + Thread.currentThread().getName());
        Thread.yield();
    }
}
