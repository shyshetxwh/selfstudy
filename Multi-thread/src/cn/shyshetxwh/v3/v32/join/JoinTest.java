package cn.shyshetxwh.v3.v32.join;

/**
 * FileName: JoinTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 18:38
 */

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            int value = (int) (Math.random() * 10000);
            System.out.println("value = " + value);
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class JoinTest {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            thread.join();
            System.out.println("thread执行完毕后再输出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
