package cn.shyshetxwh.v5.v51.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: TimerSchedule
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 21:59
 */

class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("task execute,time: " + System.currentTimeMillis());
    }
}

public class TimerSchedule {
    public static void main(String[] args) throws InterruptedException {
        long nowTime = System.currentTimeMillis();
        System.out.println("nowTime = " + nowTime);

        long scheduleTime = nowTime + 10000;
        System.out.println("scheduleTime = " + scheduleTime);

        MyTask task = new MyTask();
        MyTask task2 = new MyTask();
        Timer timer = new Timer();
        Thread.sleep(1000);

        timer.schedule(task, new Date(scheduleTime));
        timer.schedule(task2, new Date(nowTime - 5000));

        Thread.sleep(11000);
        timer.cancel();

        //Thread.sleep(Integer.MAX_VALUE);


    }
}
