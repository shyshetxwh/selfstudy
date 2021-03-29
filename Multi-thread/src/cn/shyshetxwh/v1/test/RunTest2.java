package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyThread;

/**
 * FileName: RunTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:44
 */
public class RunTest2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(300);
        System.out.println("运行结束");
    }
}
