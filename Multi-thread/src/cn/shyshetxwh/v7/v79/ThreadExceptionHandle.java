package cn.shyshetxwh.v7.v79;

/**
 * FileName: ThreadExceptionHandle
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:17
 */
public class ThreadExceptionHandle {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandle());

        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandle());

        myThread.start();
    }
}
