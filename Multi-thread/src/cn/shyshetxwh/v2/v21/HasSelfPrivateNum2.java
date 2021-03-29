package cn.shyshetxwh.v2.v21;

/**
 * FileName: HasSelfPrivateNum2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 16:54
 */

class Thread3 extends Thread {
    private HasSelfPrivateNum2 numRef;

    public Thread3(HasSelfPrivateNum2 numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}

class Thread4 extends Thread {
    private HasSelfPrivateNum2 numRef;

    public Thread4(HasSelfPrivateNum2 numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }
}

public class HasSelfPrivateNum2 {
    private int num = 0;

    public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num=" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HasSelfPrivateNum2 num2 = new HasSelfPrivateNum2();
        new Thread3(num2).start();
        new Thread4(num2).start();
    }
}
