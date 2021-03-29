package cn.shyshetxwh.v1;

/**
 * FileName: MyThread15
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 7:42
 */
public class MyThread15 extends Thread {
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (int i = 0; i < 1000; i++) {
                    //Thread.sleep(200);
                    System.out.println("i = " + i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Thread.interrupted()) {
            System.out.println("打断了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread15 thread = new MyThread15();
        thread.start();
        Thread.sleep(20);
        thread.interrupt();
    }
}
