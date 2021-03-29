package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread14;

/**
 * FileName: ThreadTest10
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 10:37
 */
public class ThreadTest10 {
    public static void main(String[] args) {
        try {
            MyThread14 thread = new MyThread14();
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(5000);
            System.out.println("退出了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
