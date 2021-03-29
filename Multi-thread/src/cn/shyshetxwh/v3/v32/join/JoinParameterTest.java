package cn.shyshetxwh.v3.v32.join;

/**
 * FileName: JoinParameterTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 18:58
 */

class MyThread2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run begin:" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("run end:" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JoinParameterTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread2 t = new MyThread2();
        t.start();

        System.out.println("main begin:" + System.currentTimeMillis());
        t.join(2000);
        Thread.sleep(2000);
        System.out.println("main end:" + System.currentTimeMillis());
    }
}
