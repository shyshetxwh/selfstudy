package cn.shyshetxwh.examples;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * FileName: BoundedHashSet
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 9:12
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemove = set.remove(o);
        if (wasRemove)
            sem.release();
        return wasRemove;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<String> boundedHashSet = new BoundedHashSet<>(10);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    boundedHashSet.add(i + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(5);
        for (String s : boundedHashSet.set) {
            System.out.println(s);
        }

        TimeUnit.SECONDS.sleep(5);
        Thread t2 = new Thread(() -> {
            System.out.println("start");
            for (int i = 0; i < 10; i++) {
                boundedHashSet.remove(i + " ");
            }
        });
        t2.start();
        TimeUnit.SECONDS.sleep(5);
        for (String s : boundedHashSet.set) {
            System.out.println(s);
        }
    }
}
