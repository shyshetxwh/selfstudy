package cn.shyshetxwh.v6.v62;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * FileName: DCLTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 15:40
 */

class OneInstanceService {
    public int i_am_has_state = 0;
    private /*volatile*/ static OneInstanceService service;

    private OneInstanceService() {
        i_am_has_state = new Random().nextInt(200) + 1;
    }

    public static OneInstanceService getService() {
        if (service == null) {
            synchronized (OneInstanceService.class) {
                if (service == null)
                    service = new OneInstanceService();
            }
        }
        return service;
    }

    public static void reset() {
        service = null;
    }
}

public class DCLTest {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            CountDownLatch latch = new CountDownLatch(1);
            CountDownLatch end = new CountDownLatch(10);
            for (int i = 0; i < 100; i++) {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            latch.await();
                            OneInstanceService service = OneInstanceService.getService();
                            if (service.i_am_has_state == 0) {
                                System.out.println("i_am_has_state==0 thread end");
                                System.exit(0);
                            }
                            end.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t1.start();
            }
            latch.countDown();
            end.await();
            OneInstanceService.reset();
        }
    }
}
