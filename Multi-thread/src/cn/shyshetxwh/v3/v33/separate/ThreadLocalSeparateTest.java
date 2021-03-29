package cn.shyshetxwh.v3.v33.separate;

/**
 * FileName: ThreadLocalSeparateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 22:23
 */

class Tools {
    public static ThreadLocal t1 = new ThreadLocal();
}

class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                if (Tools.t1.get() == null)
                    Tools.t1.set("A " + (i + 1));
                System.out.println("A get " + Tools.t1.get());
                int sleepValue = (int) (Math.random() * 1000);
                Thread.sleep(sleepValue);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                if (Tools.t1.get() == null)
                    Tools.t1.set("B " + (i + 1));
                System.out.println("    B get " + Tools.t1.get());
                int sleepValue = (int) (Math.random() * 1000);
                Thread.sleep(sleepValue);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadLocalSeparateTest {
    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();

        for (int i = 2; i < 10; i++) {
            try {
                if (Tools.t1.get() == null)
                    Tools.t1.set("main " + (i + 1));
                System.out.println("        main get " + Tools.t1.get());
                int sleepValue = (int) (Math.random() * 1000);
                Thread.sleep(sleepValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
