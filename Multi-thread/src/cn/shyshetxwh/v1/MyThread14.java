package cn.shyshetxwh.v1;

/**
 * FileName: MyThread14
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 22:50
 */
public class MyThread14 extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread14 thread = new MyThread14();
        thread.start();
        Thread.sleep(8000);
        thread.stop();
    }
}
