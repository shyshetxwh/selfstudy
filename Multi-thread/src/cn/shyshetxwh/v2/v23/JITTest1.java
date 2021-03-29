package cn.shyshetxwh.v2.v23;

/**
 * FileName: JITTest1
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:49
 */
public class JITTest1 {
    private static long x = 0;
    private static long y = 0;
    private static long a = 0;
    private static long b = 0;
    private static long c = 0;
    private static long d = 0;
    private static long e = 0;
    private static long f = 0;

    private static long count = 0;

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            c = 0;
            d = 0;
            e = 0;
            f = 0;
            count++;

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    c = 101;
                    d = 102;
                    x = b;
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    e = 201;
                    f = 202;
                    y = a;
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            String show = "count=" + count + " " + x + "," + y + "";
            if (x == 0 && y == 0) {
                System.err.println(show);
                break;
            } else {
                System.out.println(show);
            }
        }
    }
}
