package cn.shyshetxwh.v7.v71;

/**
 * FileName: BlockedTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:19
 */

class MyService {
    public synchronized static void serviceMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " enter serviceMethod()");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BlockedTest {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                MyService.serviceMethod();
            }
        }, "a");
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                MyService.serviceMethod();
            }
        }, "b");
        t2.start();

        Thread.sleep(1000);
        System.out.println("In main t2 state: " + t2.getState());
        System.out.println("In main t1 state: " + t1.getState());
    }
}
