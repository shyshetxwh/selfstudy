package cn.shyshetxwh.v1;

/**
 * FileName: MyThread17
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:29
 */
public class MyThread17 extends Thread {
    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("线程打断了");
                return;
            }
            System.out.println("timer=" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread17 thread = new MyThread17();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
