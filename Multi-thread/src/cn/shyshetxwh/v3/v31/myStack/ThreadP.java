package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: ThreadP
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:35
 */
public class ThreadP extends Thread {
    private Producer p;

    public ThreadP(Producer p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true)
            p.pushService();
    }
}
