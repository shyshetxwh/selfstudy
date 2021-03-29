package cn.shyshetxwh.v1;

/**
 * FileName: MyThread2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:52
 */
public class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("run= "+Thread.currentThread().getName());
        }
    }
}
