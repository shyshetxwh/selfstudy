package cn.shyshetxwh.v7.v79;

/**
 * FileName: StateUncaughtExceptionHandle
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:16
 */
public class StateUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("static exception handle");
        e.printStackTrace();
    }
}
