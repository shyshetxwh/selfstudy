package cn.shyshetxwh.v2.v22;

/**
 * FileName: SynchronizedTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 22:17
 */

class Task2 {
    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("nosynchronized threadName=" + Thread.currentThread().getName()
                    + " i=" + (i + 1));
        }
        System.out.println("");
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("synchronized threadName=" + Thread.currentThread().getName()
                        + " i=" + (i + 1));
            }
        }
    }

}

class Thread5 extends Thread {
    private Task2 task;

    public Thread5(Task2 task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.doLongTimeTask();
    }
}

class Thread6 extends Thread {
    private Task2 task;

    public Thread6(Task2 task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.doLongTimeTask();
    }
}

public class SynchronizedTest2 {
    public static void main(String[] args) {
        Task2 task2 = new Task2();

        new Thread5(task2).start();
        new Thread6(task2).start();
    }

}
