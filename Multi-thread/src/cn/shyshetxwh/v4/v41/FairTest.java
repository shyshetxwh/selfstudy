package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: FairTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 17:35
 */
public class FairTest {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock(true);
        System.out.println(lock1.isFair());

        ReentrantLock lock2 = new ReentrantLock(false);
        System.out.println(lock2.isFair());

        ReentrantLock lock3 = new ReentrantLock();
        System.out.println(lock3.isFair());
    }
}
