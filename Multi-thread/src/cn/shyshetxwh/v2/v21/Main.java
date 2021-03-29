package cn.shyshetxwh.v2.v21;

/**
 * FileName: Main
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 18:52
 */
public class Main {
    protected int i = 10;

    public synchronized void operateMainMethod() {
        try {
            i--;
            System.out.println("main i = " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
