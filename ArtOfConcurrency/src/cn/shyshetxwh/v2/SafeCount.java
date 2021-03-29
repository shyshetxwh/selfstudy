package cn.shyshetxwh.v2;


import java.util.ArrayList;
import java.util.List;

/**
 * FileName: SafeCount
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/9 0009 9:35
 */
public class SafeCount {
    public static void main(String[] args) {
        Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        cas.safeCount();
                        cas.count();
                    }
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.getI());
        System.out.println(cas.getAtomicInteger().get());
        System.out.println(System.currentTimeMillis() - start);
    }
}
