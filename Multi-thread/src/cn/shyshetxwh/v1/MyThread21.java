package cn.shyshetxwh.v1;

/**
 * FileName: MyThread21
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 10:08
 */
public class MyThread21 extends Thread {
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            Thread.yield();
            count += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin) + "毫秒");
    }

    public static void main(String[] args) {
        MyThread21 thread = new MyThread21();
        thread.start();
    }
}
