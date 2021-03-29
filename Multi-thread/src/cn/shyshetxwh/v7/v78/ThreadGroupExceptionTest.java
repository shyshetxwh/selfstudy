package cn.shyshetxwh.v7.v78;

/**
 * FileName: ThreadGroupExceptionTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:05
 */

class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        this.interrupt();
    }
}

class MyThread2 extends Thread {
    private String num;

    public MyThread2(ThreadGroup group, String name, String num) {
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        int i = Integer.parseInt(num);
        while (!this.isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

public class ThreadGroupExceptionTest {
    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("myGroup");
        MyThread2[] threads = new MyThread2[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread2(group, "thread " + (i + 1), "1");
            threads[i].start();
        }

        MyThread2 errorThread = new MyThread2(group, "errorThread", "a");
        errorThread.start();
    }
}
