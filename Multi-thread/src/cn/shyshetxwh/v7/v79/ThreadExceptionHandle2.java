package cn.shyshetxwh.v7.v79;

/**
 * FileName: ThreadExceptionHandle2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 23:19
 */
public class ThreadExceptionHandle2 {
    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("myGroup");
        Thread myThread = new MyThread(group, "myThread");

        //myThread.setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandle());

        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandle());

        myThread.start();
    }
}
