package cn.shyshetxwh.v1.test;

/**
 * FileName: SleepTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 19:44
 */
public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.currentThread().sleep(2000, 999999);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
