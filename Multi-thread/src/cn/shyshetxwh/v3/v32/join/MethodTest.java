package cn.shyshetxwh.v3.v32.join;

/**
 * FileName: MethodTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 19:24
 */
public class MethodTest {
    public void f() {
        try {
            synchronized (this) {
                System.out.println(this);
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void g() {
        try {
            synchronized (this) {
                System.out.println(this);
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MethodTest test = new MethodTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.g();
            }
        }).start();
        System.out.println("block?");
    }
}
