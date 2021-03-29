package cn.shyshetxwh.v1;


/**
 * FileName: ConcurrencyTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/9 0009 8:04
 */
public class ConcurrencyTest {
    private static void concurrency(long count) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        t.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        t.join();
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial(long count) {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial :" + time + "ms,b=" + b + ",a=" + a);
    }

    private static void method(long count) throws InterruptedException {
        System.out.println("test count: " + count);
        concurrency(count);
        serial(count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            final int n = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        method((long) Math.pow(10, (n + 4)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            t.join();
        }
    }
}
