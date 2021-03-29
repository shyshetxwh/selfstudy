package cn.shyshetxwh.v2.v21;

/**
 * FileName: ExtendsTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 18:59
 */

class Thread7 extends Thread {
    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operateSubMethod();
    }
}

public class ExtendsTest {
    public static void main(String[] args) {
        new Thread7().start();
    }
}
