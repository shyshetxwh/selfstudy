package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: ThreadC
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:36
 */
public class ThreadC extends Thread {
    private Consumer c;

    public ThreadC(Consumer c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true)
            c.popService();
    }
}
