package cn.shyshetxwh.v1;

/**
 * FileName: MyThread11
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 22:27
 */
public class MyThread11 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50000; i++) {
            if (this.interrupted()) {
                System.out.println("已经停止，退出");
                break;
            }
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args) {
        try {
            MyThread11 thread = new MyThread11();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
