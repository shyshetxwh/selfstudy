package cn.shyshetxwh.v1;

/**
 * FileName: MyThread12
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 22:36
 */
public class MyThread12 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            MyThread12 thread = new MyThread12();
            thread.start();
            Thread.sleep(200);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
