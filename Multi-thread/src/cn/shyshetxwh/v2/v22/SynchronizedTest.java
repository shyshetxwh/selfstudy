package cn.shyshetxwh.v2.v22;

/**
 * FileName: SynchronizedTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 20:21
 */

class CommonUtils {
    public static long beginTime1;
    public static long endTime1;

    public static long beginTime2;
    public static long endTime2;
}

class Task {
    private String getData1;
    private String getData2;

    public synchronized void doLongTimeTask() {
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            getData1 = "长时间处理任务后从远程返回的值1 threadName=" + Thread.currentThread().getName();
            getData2 = "长时间处理任务后从远程返回的值2 threadName=" + Thread.currentThread().getName();
            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread {
    private Task task;

    public Thread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        CommonUtils.beginTime1 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime1 = System.currentTimeMillis();
    }
}

class Thread2 extends Thread {
    private Task task;

    public Thread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        CommonUtils.beginTime2 = System.currentTimeMillis();
        task.doLongTimeTask();
        CommonUtils.endTime2 = System.currentTimeMillis();
    }
}

public class SynchronizedTest {
    public static void main(String[] args) {
        Task task = new Task();

        Thread1 thread1 = new Thread1(task);
        thread1.start();

        Thread2 thread2 = new Thread2(task);
        thread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime2 < CommonUtils.beginTime1) {
            beginTime = CommonUtils.beginTime2;
        }

        long endTime = CommonUtils.endTime1;
        if (CommonUtils.endTime2 > CommonUtils.endTime1) {
            endTime = CommonUtils.endTime2;
        }

        System.out.println("耗时：" + (endTime - beginTime) / 1000);
    }
}
