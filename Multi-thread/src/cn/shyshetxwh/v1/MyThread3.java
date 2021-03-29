package cn.shyshetxwh.v1;

/**
 * FileName: MyThread3
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:57
 */
public class MyThread3 extends Thread {
    private int i;

    public MyThread3(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
    }
}
