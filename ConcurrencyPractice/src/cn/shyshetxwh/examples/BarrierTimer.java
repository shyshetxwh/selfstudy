package cn.shyshetxwh.examples;

/**
 * FileName: BarrierTimer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 16:54
 */
public class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    @Override
    public synchronized void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
        } else
            endTime = t;
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }

}
