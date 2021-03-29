package cn.shyshetxwh.v2.v21;

/**
 * FileName: Main2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 19:29
 */
public class Main2 {
    public synchronized void serviceMethod() {
        try {
            System.out.println("main begin threadName="
                    + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("main end threadName="
                    + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
