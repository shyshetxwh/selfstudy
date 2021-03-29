package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread;

/**
 * FileName: RunTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:42
 */
public class RunTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束");
    }
}
