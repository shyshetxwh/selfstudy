package cn.shyshetxwh.v5.v51.schedule;

import java.util.Date;
import java.util.Timer;

/**
 * FileName: RepeatTask
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 22:31
 */
public class RepeatTask {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println("now = " + now);

        long schedule = now + 10000;
        System.out.println("schedule = " + schedule);

        MyTask myTask = new MyTask();
        Timer timer = new Timer();

        timer.schedule(myTask, new Date(schedule), 4000);


    }
}
