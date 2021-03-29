package cn.shyshetxwh.v2.v22;

/**
 * FileName: DealThreadTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 15:48
 */

class DealThread implements Runnable {
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("lock1->lock2");
                }
            }
        }

        if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("lock2->lock1");
                }
            }
        }
    }
}

public class DealThreadTest {
    public static void main(String[] args) throws InterruptedException {
        DealThread t = new DealThread();

        t.setFlag("a");
        new Thread(t).start();

        Thread.sleep(100);

        t.setFlag("b");
        new Thread(t).start();
    }
}
