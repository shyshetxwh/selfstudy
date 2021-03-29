package cn.shyshetxwh.v3.v31;


import java.util.ArrayList;
import java.util.List;

/**
 * FileName: WaitWhile
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 20:08
 */

class ValueObject {
    public static List list = new ArrayList();
}

class Add {
    private String lock;

    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}

class Subtract {
    private String lock;

    public Subtract(String lock) {
        this.lock = lock;
    }

    public void subtract() {
        try {
            synchronized (lock) {
                while (ValueObject.list.size() == 0) {
                    System.out.println("wait begin threadName=" + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait   end threadName=" + Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size=" + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadAdd extends Thread {
    private Add add;

    public ThreadAdd(Add add) {
        this.add = add;
    }

    @Override
    public void run() {
        add.add();
    }
}

class ThreadSubtract extends Thread {
    private Subtract sub;

    public ThreadSubtract(Subtract sub) {
        this.sub = sub;
    }

    @Override
    public void run() {
        sub.subtract();
    }
}

public class WaitWhile {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");

        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);

        ThreadSubtract threadSubtract1 = new ThreadSubtract(subtract);
        threadSubtract1.setName("threadSubtract1");
        threadSubtract1.start();

        ThreadSubtract threadSubtract2 = new ThreadSubtract(subtract);
        threadSubtract2.setName("threadSubtract2");
        threadSubtract2.start();


        Thread.sleep(1000);

        ThreadAdd threadAdd = new ThreadAdd(add);
        threadAdd.setName("threadAdd");
        threadAdd.start();


    }
}
