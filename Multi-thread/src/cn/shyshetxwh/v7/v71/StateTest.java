package cn.shyshetxwh.v7.v71;

/**
 * FileName: StateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:06
 */

class MyThread extends Thread {
    public MyThread() {
        System.out.println("construct: Thread.currentThread().getState() " + Thread.currentThread().getState());
        System.out.println("this.getState() " + this.getState());
    }

    @Override
    public void run() {
        System.out.println("run Thread.currentThread().getState() " + Thread.currentThread().getState());
    }
}


public class StateTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        System.out.println("In main thread state: " + thread.getState());
        Thread.sleep(1000);
        thread.start();
        Thread.sleep(1000);
        System.out.println("In main thread state: " + thread.getState());
    }
}
