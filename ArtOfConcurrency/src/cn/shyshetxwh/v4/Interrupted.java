package cn.shyshetxwh.v4;

/**
 * FileName: Interrupted
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/9 0009 23:02
 */
public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    SleepUtils.second(10);
            }
        }, "SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                }
            }
        }, "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        SleepUtils.second(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread.isInterrupted()=" + sleepThread.isInterrupted());
        System.out.println(" busyThread.isInterrupted()=" + busyThread.isInterrupted());

        SleepUtils.second(2);
    }
}
