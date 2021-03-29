package cn.shyshetxwh.v7.v79;

/**
 * FileName: MyThreadGroup
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:13
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        System.out.println("threadGroup exception handle");
        e.printStackTrace();
    }
}
