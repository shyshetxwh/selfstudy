package cn.shyshetxwh.v1;

/**
 * FileName: MyServiceThread2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:22
 */
public class MyServiceThread2 extends Thread {
    private MyService object;

    public MyServiceThread2(MyService object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.println("username=" + object.getUsername());
        System.out.println("password=" + object.getPassword());
    }
}
