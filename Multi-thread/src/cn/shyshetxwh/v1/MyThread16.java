package cn.shyshetxwh.v1;

/**
 * FileName: MyThread16
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:06
 */
public class MyThread16 extends Thread {
    @Override
    public void run() {
        try {
            this.stop();
        } catch (ThreadDeath e) {
            System.out.println("catch");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread16 thread = new MyThread16();
        thread.start();
    }
}
