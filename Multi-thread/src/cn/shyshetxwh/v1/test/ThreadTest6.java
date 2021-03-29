package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread6;

/**
 * FileName: ThreadTest6
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:01
 */
public class ThreadTest6 {
    public static void main(String[] args) {
        MyThread6 myThread6 = new MyThread6();

        new Thread(myThread6).start();
        new Thread(myThread6).start();
        new Thread(myThread6).start();
        new Thread(myThread6).start();
        new Thread(myThread6).start();
    }
}
