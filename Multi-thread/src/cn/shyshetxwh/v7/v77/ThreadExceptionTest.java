package cn.shyshetxwh.v7.v77;

/**
 * FileName: ThreadExceptionTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 22:50
 */

class MyThread extends Thread {
    @Override
    public void run() {
        String username = null;
        System.out.println(username.hashCode());
    }
}

public class ThreadExceptionTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("t1");
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("thread " + t.getName() + " occurs " + e.getMessage());
                e.printStackTrace();
            }
        });
        t1.start();

        MyThread t2 = new MyThread();
        t2.setName("t2");
        t2.start();
    }
}
