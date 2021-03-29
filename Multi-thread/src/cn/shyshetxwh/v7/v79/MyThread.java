package cn.shyshetxwh.v7.v79;

/**
 * FileName: MyThread
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:12
 */
public class MyThread extends Thread {
    private String num = "a";

    public MyThread() {
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        int i = Integer.parseInt(num);
        System.out.println("thread print " + (i + 1));
    }
}
