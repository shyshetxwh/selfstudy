package cn.shyshetxwh.v1;

/**
 * FileName: MyThread18
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:48
 */
public class MyThread18 extends Thread {
    @Override
    public void run() {
        if (Thread.interrupted()) {
            System.out.println("insert info");
            return;
        }

        if (Thread.interrupted()) {
            System.out.println("update info");
            return;
        }

        if (Thread.interrupted()) {
            System.out.println("delete info");
            return;
        }

        if (Thread.interrupted()) {
            System.out.println("select info");
            return;
        }

        System.out.println("fo fo fo fo");
    }
}
