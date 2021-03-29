package cn.shyshetxwh.v1.test;

/**
 * FileName: ThreadIDTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 20:30
 */
public class ThreadIDTest {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": " + thread.getId());
    }
}
