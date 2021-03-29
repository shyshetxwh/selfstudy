package cn.shyshetxwh.v7.v72;

/**
 * FileName: ParentGroup
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:44
 */
public class ParentGroup {
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup(mainGroup, "A");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("runMethod!");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(group, r);
        t.setName("Z");
        t.start();

        ThreadGroup[] listGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(listGroup);
        System.out.println("In main has " + listGroup.length + " threadGroup");
        for (ThreadGroup threadGroup : listGroup) {
            System.out.println("threadGroup = " + threadGroup);
        }
        Thread[] threads = new Thread[listGroup[0].activeCount()];
        listGroup[0].enumerate(threads);
        System.out.println(threads[0].getName());
    }
}
