package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread8;

/**
 * FileName: ThreadTest81
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:36
 */
public class ThreadTest81 {
    public static void main(String[] args) {
        MyThread8 myThread8 = new MyThread8();
        System.out.println("begin =" + System.currentTimeMillis());
        myThread8.run();
        System.out.println("end =" + System.currentTimeMillis());
    }
}
