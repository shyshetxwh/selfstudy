package cn.shyshetxwh.v7.v72;

/**
 * FileName: ThreadGroupTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:36
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread name=" + Thread.currentThread().getName());
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread name=" + Thread.currentThread().getName());
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "b");

        ThreadGroup group = new ThreadGroup("group");

        new Thread(group, a).start();
        new Thread(group, b).start();

        System.out.println("active thread: " + group.activeCount());
        System.out.println("group name: " + group.getName());
    }
}
