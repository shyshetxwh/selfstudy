package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.CountOperate2;

/**
 * FileName: CountOperateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:15
 */
public class CountOperateTest2 {
    public static void main(String[] args) {
        CountOperate2 operate = new CountOperate2();
        Thread t = new Thread(operate);
        System.out.println("main begin t isAlive" + t.isAlive());
        t.setName("A");
        t.start();
        System.out.println("main end t isAlive" + t.isAlive());
    }
}
