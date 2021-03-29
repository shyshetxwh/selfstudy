package cn.shyshetxwh.v1;

/**
 * FileName: MyThread9
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 21:16
 */
public class MyThread9 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i = " + (i + 1));
        }
    }
}
