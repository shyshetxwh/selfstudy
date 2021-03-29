package cn.shyshetxwh.v1;

/**
 * FileName: MyThread5
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:16
 */
public class MyThread5 extends Thread {
    private int count=5;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println(this.currentThread().getName()+":count= "+count);
    }
}
