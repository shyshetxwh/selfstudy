package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: ThreadProducer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:14
 */
public class ThreadProducer extends Thread {
    private ProducerService ps;

    public ThreadProducer(ProducerService ps) {
        this.ps = ps;
    }

    @Override
    public void run() {
        while (true)
            ps.producer();
    }
}
