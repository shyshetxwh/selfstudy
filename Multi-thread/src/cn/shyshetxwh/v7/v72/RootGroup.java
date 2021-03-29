package cn.shyshetxwh.v7.v72;

/**
 * FileName: RootGroup
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 20:07
 */
public class RootGroup {
    public static void main(String[] args) {
        System.out.println("Thread: " + Thread.currentThread().getName() +
                " from " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("main group from " + Thread.currentThread().getThreadGroup().getParent().getName());
    }
}
