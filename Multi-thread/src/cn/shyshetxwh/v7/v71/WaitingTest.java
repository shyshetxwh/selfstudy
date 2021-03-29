package cn.shyshetxwh.v7.v71;

/**
 * FileName: WaitingTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:27
 */

class Lock {
    public static final Byte lock = new Byte("0");
}

public class WaitingTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (Lock.lock) {
                        Lock.lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        Thread.sleep(1000);
        System.out.println("In main t state: " + t.getState());
    }
}
