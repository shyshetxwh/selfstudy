package cn.shyshetxwh.v2.v23;

/**
 * FileName: DeadCycle
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:11
 */

class RunThread extends Thread {
    private volatile boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("enter run()");
        while (isRunning == true) {
        }
        System.out.println("thread stop");
    }
}

public class DeadCycle {
    public static void main(String[] args) throws InterruptedException {
        RunThread thread = new RunThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);
        System.out.println("thread.setRunning(false)");
    }
}
