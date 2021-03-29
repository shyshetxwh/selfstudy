package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread5;

/**
 * FileName: ThreadTest5
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:17
 */
public class ThreadTest5 {
    public static void main(String[] args) {
        MyThread5 myThread5 = new MyThread5();
        new Thread(myThread5,"A").start();
        new Thread(myThread5,"B").start();
        new Thread(myThread5,"C").start();
        new Thread(myThread5,"D").start();
        new Thread(myThread5,"E").start();
    }
}
