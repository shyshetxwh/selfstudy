package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyRunnable;

/**
 * FileName: RunnableTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:01
 */
public class RunnableTest {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println("运行结束！");
    }
}
