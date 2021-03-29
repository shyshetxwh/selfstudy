package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.CountOperate;

/**
 * FileName: CountOperateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:15
 */
public class CountOperateTest {
    public static void main(String[] args) {
        CountOperate operate = new CountOperate();
        Thread t = new Thread(operate);
        t.setName("A");
        t.start();
    }
}
