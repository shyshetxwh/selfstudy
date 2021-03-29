package cn.shyshetxwh.v3.v31.myStack;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: MyStack
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:23
 */
public class MyStack {
    private List list = new ArrayList();

    public synchronized void push() {
        try {
            while (list.size() == 1) {
                System.out.println("push(): " + Thread.currentThread().getName() + " waiting");
                this.wait();
            }
            list.add("anyString=" + Math.random());
            this.notifyAll();
            System.out.println("push=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String pop() {
        String returnValue = "";

        try {
            while (list.size() == 0) {
                System.out.println("pop(): " + Thread.currentThread().getName() + " waiting");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            this.notifyAll();
            System.out.println("pop=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
