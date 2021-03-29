package cn.shyshetxwh.v4.v41;

import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: LockCountTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 16:21
 */

class MyService {
    private ReentrantLock lock = new ReentrantLock();

    public void testMethod1() {
        System.out.println("A " + lock.getHoldCount());
        lock.lock();
        System.out.println("B " + lock.getHoldCount());
        testMethod2();
        System.out.println("F " + lock.getHoldCount());
        lock.unlock();
        System.out.println("G " + lock.getHoldCount());
    }

    private void testMethod2() {
        System.out.println("C " + lock.getHoldCount());
        lock.lock();
        System.out.println("D " + lock.getHoldCount());
        lock.unlock();
        System.out.println("E " + lock.getHoldCount());
    }

}

public class LockCountTest {
    public static void main(String[] args) {
        MyService service = new MyService();
        service.testMethod1();
    }
}
