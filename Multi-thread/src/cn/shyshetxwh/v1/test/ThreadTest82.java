package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread8;

/**
 * FileName: ThreadTest82
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:40
 */
public class ThreadTest82 {
    public static void main(String[] args) {
        MyThread8 myThread8 = new MyThread8();
        System.out.println("begin =" + System.currentTimeMillis());
        myThread8.start();
        System.out.println("end =" + System.currentTimeMillis());
    }
}
