package cn.shyshetxwh.v1;

/**
 * FileName: MyServiceThread1
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:20
 */
public class MyServiceThread1 extends Thread {
    private MyService object;

    public MyServiceThread1(MyService object) {
        super();
        this.object = object;
    }

    @Override
    public void run() {
        object.printString("b", "bb");
    }
}
