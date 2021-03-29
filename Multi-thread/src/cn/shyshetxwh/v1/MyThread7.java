package cn.shyshetxwh.v1;

/**
 * FileName: MyThread7
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:18
 */
public class MyThread7 extends Thread {
    @Override
    public void run() {
        System.out.println("run=" + this.isAlive());
    }
}
