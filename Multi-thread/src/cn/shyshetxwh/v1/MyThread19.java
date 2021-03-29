package cn.shyshetxwh.v1;

/**
 * FileName: MyThread19
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:52
 */
public class MyThread19 extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            i++;
        }
    }

    public static void main(String[] args) {
        try {
            MyThread19 thread = new MyThread19();
            thread.start();
            Thread.sleep(5000);

            thread.suspend();
            System.out.println("A= " + System.currentTimeMillis() + " i=" + thread.getI());
            Thread.sleep(5000);
            System.out.println("A= " + System.currentTimeMillis() + " i=" + thread.getI());

            thread.resume();
            Thread.sleep(5000);

            thread.suspend();
            System.out.println("B= " + System.currentTimeMillis() + " i=" + thread.getI());
            Thread.sleep(5000);
            System.out.println("B= " + System.currentTimeMillis() + " i=" + thread.getI());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
