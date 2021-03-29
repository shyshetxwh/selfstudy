package cn.shyshetxwh.v1;

/**
 * FileName: MyThread13
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 22:46
 */
public class MyThread13 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run begin");
            super.run();
            for (int i = 0; i < 100000; i++) {
                System.out.println("i = " + (i + 1));
            }
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread13 thread = new MyThread13();
        thread.start();
        thread.interrupt();
        System.out.println("end");
    }
}
