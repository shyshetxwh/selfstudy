package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread3;

/**
 * FileName: ThreadTest3
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:58
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        for (int i = 0; i < 13; i++) {
            new MyThread3(i).start();
        }
    }
}
