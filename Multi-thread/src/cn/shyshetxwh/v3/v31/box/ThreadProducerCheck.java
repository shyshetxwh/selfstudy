package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: ThreadProducerCheck
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:15
 */
public class ThreadProducerCheck extends Thread {
    private ProducerService ps;

    public ThreadProducerCheck(ProducerService ps) {
        this.ps = ps;
    }

    @Override
    public void run() {
        ps.checkBoxStatus();
    }
}
