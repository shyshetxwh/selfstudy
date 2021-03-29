package cn.shyshetxwh.v1;

/**
 * FileName: MyThread20
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 9:33
 */
public class MyThread20 extends Thread {
    private long i = 0;

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println("i = " + i);
        }
    }

    public static void main(String[] args) {
        try {
            MyThread20 thread = new MyThread20();
            thread.start();
            Thread.sleep(1000);
            thread.suspend();
            System.out.println("main end!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
