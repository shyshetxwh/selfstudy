package cn.shyshetxwh.v7.v78;

/**
 * FileName: ThreadGroupTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 22:58
 */

class MyThread extends Thread {
    private String num;

    public MyThread(ThreadGroup group, String name, String num) {
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        try {
            int i = Integer.parseInt(num);
            while (true) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("myGroup");
        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread(group, "thread " + (i + 1), "1");
            threads[i].start();
        }

        MyThread errorThread = new MyThread(group, "errorThread", "a");
        errorThread.start();
    }
}
