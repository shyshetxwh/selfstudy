package cn.shyshetxwh.v1;

/**
 * FileName: MyThread22
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 10:13
 */
public class MyThread22 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread22 run priority=" + this.getPriority());
        Thread thread = new MyThread23();
        thread.start();
    }
}
