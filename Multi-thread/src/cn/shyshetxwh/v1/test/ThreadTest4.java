package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread4;

/**
 * FileName: ThreadTest4
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:12
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        new MyThread4("A").start();
        new MyThread4("B").start();
        new MyThread4("C").start();
    }
}
