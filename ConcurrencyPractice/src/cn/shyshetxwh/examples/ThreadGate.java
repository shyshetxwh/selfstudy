package cn.shyshetxwh.examples;

import net.jcip.annotations.ThreadSafe;

/**
 * FileName: ThreadGate
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 22:44
 */
@ThreadSafe
public class ThreadGate {
    private boolean isOpen;
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
