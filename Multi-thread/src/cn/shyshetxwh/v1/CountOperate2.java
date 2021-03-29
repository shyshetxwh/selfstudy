package cn.shyshetxwh.v1;

/**
 * FileName: CountOperate
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:12
 */
public class CountOperate2 extends Thread {
    public CountOperate2() {
        System.out.println("CountOperate2 begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("CountOperate2 end");
    }

    @Override
    public void run() {
        System.out.println("run begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("run end");
    }
}
