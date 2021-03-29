package cn.shyshetxwh.v2.v21;

/**
 * FileName: ExtendsTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 19:34
 */

class Thread10 extends Thread {
    private Sub2 sub2;

    public Thread10(Sub2 sub2) {
        this.sub2 = sub2;
    }

    @Override
    public void run() {
        sub2.serviceMethod();
    }
}

class Thread11 extends Thread {
    private Sub2 sub2;

    public Thread11(Sub2 sub2) {
        this.sub2 = sub2;
    }

    @Override
    public void run() {
        sub2.serviceMethod();
    }
}

public class ExtendsTest2 {
    public static void main(String[] args) {
        Sub2 sub2 = new Sub2();

        Thread10 thread10 = new Thread10(sub2);
        thread10.setName("A");
        thread10.start();

        Thread11 thread11 = new Thread11(sub2);
        thread11.setName("B");
        thread11.start();
    }
}
