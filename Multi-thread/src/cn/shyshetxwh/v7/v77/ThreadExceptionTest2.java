package cn.shyshetxwh.v7.v77;

/**
 * FileName: ThreadExceptionTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 22:54
 */
public class ThreadExceptionTest2 {
    public static void main(String[] args) {
        MyThread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("thread " + t.getName() + " occurs " + e.getMessage());
                e.printStackTrace();
            }
        });

        MyThread t1 = new MyThread();
        t1.setName("t1");
        t1.start();

        MyThread t2 = new MyThread();
        t2.setName("t2");
        t2.start();
    }
}
