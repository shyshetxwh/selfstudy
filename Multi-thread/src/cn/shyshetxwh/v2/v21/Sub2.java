package cn.shyshetxwh.v2.v21;

/**
 * FileName: Sub2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 19:33
 */
public class Sub2 extends Main2 {
    @Override
    public synchronized void serviceMethod() {
        try {
            System.out.println("sub begin threadName="
                    + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("sub end threadName="
                    + Thread.currentThread().getName()
                    + " time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
