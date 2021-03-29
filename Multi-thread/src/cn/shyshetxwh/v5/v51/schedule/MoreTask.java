package cn.shyshetxwh.v5.v51.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: MoreTask
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 22:21
 */

class MyTask1 extends TimerTask {
    @Override
    public void run() {
        try {
            System.out.println("task1 begin time=" + System.currentTimeMillis());
            Thread.sleep(20000);
            System.out.println("task1   end time=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyTask2 extends TimerTask {
    @Override
    public void run() {
        System.out.println("task2 begin time=" + System.currentTimeMillis());
        System.out.println("task2   end time=" + System.currentTimeMillis());
    }
}

public class MoreTask {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println("now = " + now);

        long schedule1 = now;
        System.out.println("schedule1 = " + schedule1);
        long schedule2 = now + 5000;
        System.out.println("schedule2 = " + schedule2);

        MyTask1 task1 = new MyTask1();
        MyTask2 task2 = new MyTask2();

        Timer timer = new Timer();
        timer.schedule(task1, new Date(schedule1));
        timer.schedule(task2, new Date(schedule2));


    }
}
