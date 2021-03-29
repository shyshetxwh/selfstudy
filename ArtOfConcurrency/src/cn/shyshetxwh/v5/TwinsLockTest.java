package cn.shyshetxwh.v5;

import cn.shyshetxwh.v4.SleepUtils;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * FileName: TwinsLockTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/10 0010 22:02
 */
public class TwinsLockTest {
    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }

        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }

}
