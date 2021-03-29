package cn.shyshetxwh.v1;

/**
 * FileName: MyThread10
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 21:22
 */
public class MyThread10 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            if (!this.isInterrupted()) {
                System.out.println("i = " + (i + 1));

            }
        }
    }
}
