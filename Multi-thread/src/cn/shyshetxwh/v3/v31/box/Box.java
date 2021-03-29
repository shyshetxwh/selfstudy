package cn.shyshetxwh.v3.v31.box;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Box
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:58
 */
public class Box {
    private static List list = new ArrayList();

    public synchronized void add() {
        if (size() < 50) {
            list.add("anyString");
            System.out.println("Thread: " + Thread.currentThread().getName() +
                    "execute add() and size=" + size());
        }
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized Object popFirst() {
        Object value = list.remove(0);
        System.out.println("Thread: " + Thread.currentThread().getName() +
                "execute popFirst() and size=" + size());
        return value;
    }
}
