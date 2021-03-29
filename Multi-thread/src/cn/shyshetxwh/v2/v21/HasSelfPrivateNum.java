package cn.shyshetxwh.v2.v21;

/**
 * FileName: HasSelfPrivateNum
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 16:46
 */

class Thread1 extends Thread {
    private HasSelfPrivateNum numRef;

    public Thread1(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}

class Thread2 extends Thread {
    private HasSelfPrivateNum numRef;

    public Thread2(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }
}

public class HasSelfPrivateNum {
    public void addI(String username) {
        try {
            int num = 0;
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
        HasSelfPrivateNum num = new HasSelfPrivateNum();
        new Thread1(num).start();
        new Thread2(num).start();
    }
}
