package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: ThreadConsumer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:23
 */
public class ThreadConsumer extends Thread {
    private ConsumerService cs;

    public ThreadConsumer(ConsumerService cs) {
        this.cs = cs;
    }

    @Override
    public void run() {
        while (true)
            cs.consumer();
    }
}
