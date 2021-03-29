package cn.shyshetxwh.v7.v72;

/**
 * FileName: AutoAddGroup
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:55
 */
public class AutoAddGroup {
    public static void main(String[] args) {
        System.out.println("A threadName: " + Thread.currentThread().getName()
                + " from group: " + Thread.currentThread().getThreadGroup().getName() + " "
                + " group has " + Thread.currentThread().getThreadGroup().activeCount() + " group");
        ThreadGroup newGroup = new ThreadGroup("new Group");
        System.out.println("B threadName: " + Thread.currentThread().getName()
                + " from group: " + Thread.currentThread().getThreadGroup().getName() + " "
                + " group has " + Thread.currentThread().getThreadGroup().activeCount() + " group");
        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroups);
        for (ThreadGroup threadGroup : threadGroups) {
            System.out.println(threadGroup);
        }
    }
}
