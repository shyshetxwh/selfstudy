package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: ThreadConsumerCheck
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:24
 */
public class ThreadConsumerCheck extends Thread {
    private ConsumerService cs;

    public ThreadConsumerCheck(ConsumerService cs) {
        this.cs = cs;
    }

    @Override
    public void run() {
        cs.checkBoxStatus();
    }
}
