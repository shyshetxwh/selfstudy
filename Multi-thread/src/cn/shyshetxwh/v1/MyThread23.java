package cn.shyshetxwh.v1;

/**
 * FileName: MyThread23
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 10:15
 */
public class MyThread23 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread23 run priority=" + this.getPriority());
    }

    public static void main(String[] args) {
        System.out.println("main thread begin priority=" + Thread.currentThread().getPriority());
        MyThread22 thread = new MyThread22();
        thread.start();
    }
}
