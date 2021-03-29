package cn.shyshetxwh.v3.v34;

/**
 * FileName: ThreadLocalNoExtends
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 22:49
 */

class Tools {
    //public static ThreadLocal t = new ThreadLocal();
    public static InheritableThreadLocal t = new InheritableThreadLocal();
}

class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("In Thread1 get:" + Tools.t.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadLocalNoExtends {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            if (Tools.t.get() == null)
                Tools.t.set("put in main");
            System.out.println("    In Main get:" + Tools.t.get());
            Thread.sleep(100);
        }

        Thread.sleep(5000);
        new Thread1().start();
        Thread.sleep(100);
        Tools.t.set("put new in main");
        System.out.println("    In Main get:" + Tools.t.get());
    }
}
