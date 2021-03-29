package cn.shyshetxwh.v2.v23;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FileName: AtomicIntegerTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:46
 */

class AddCountThread extends Thread {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(count.incrementAndGet());
        }
    }
}

public class AtomicIntegerTest {
    public static void main(String[] args) {
        AddCountThread thread = new AddCountThread();

        for (int i = 0; i < 5; i++) {
            new Thread(thread).start();
        }
    }
}
