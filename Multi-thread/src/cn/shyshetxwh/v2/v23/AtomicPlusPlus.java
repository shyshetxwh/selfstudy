package cn.shyshetxwh.v2.v23;

/**
 * FileName: AtomicPlusPlus
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:40
 */

class MyThread extends Thread {
    public volatile static int count;

    private /*synchronized*/ static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }
}

public class AtomicPlusPlus {
    public static void main(String[] args) {
        MyThread[] threads = new MyThread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
