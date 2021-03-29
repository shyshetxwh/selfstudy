package cn.shyshetxwh.v2.v21;

import java.util.Date;

/**
 * FileName: HoldsLockTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 19:41
 */
public class HoldsLockTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("A " + Thread.currentThread().holdsLock(HoldsLockTest.class));
        synchronized (HoldsLockTest.class) {
            System.out.println("B " + Thread.currentThread().holdsLock(HoldsLockTest.class));
            System.out.println("D " + Thread.currentThread().holdsLock(date));
        }
        System.out.println("C " + Thread.currentThread().holdsLock(HoldsLockTest.class));
        synchronized (date) {
            System.out.println("E " + Thread.currentThread().holdsLock(date));
            for (int i = 0; i < 10; i++) {
                System.out.println("i = " + i);
            }
        }
    }
}
