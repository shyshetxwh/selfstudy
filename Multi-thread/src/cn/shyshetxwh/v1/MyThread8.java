package cn.shyshetxwh.v1;

/**
 * FileName: MyThread8
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:33
 */
public class MyThread8 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("run threadName=" + this.currentThread().getName() + " begin");
            System.out.println("this.getName()=" + this.getName());
            Thread.sleep(2000);
            System.out.println("run threadName=" + this.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
