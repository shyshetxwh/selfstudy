package cn.shyshetxwh.v7.v79;

/**
 * FileName: ObjectUncaughtExceptionHandle
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:15
 */
public class ObjectUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("object exception handle");
        e.printStackTrace();
    }
}
