package cn.shyshetxwh.v5.v51.scheduleAtFixedRate;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: ScheduleFixAtRateTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 23:08
 */

public class ScheduleFixAtRateTest {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println("now = " + now);
        long schedule = now - 20000;
        System.out.println("schedule = " + schedule);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("begin time " + System.currentTimeMillis());
                System.out.println("  end time " + System.currentTimeMillis());
            }
        }, new Date(schedule), 2000);
    }
}
