package cn.shyshetxwh.v5.v51.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: IntervalExecute
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 22:44
 */
public class IntervalExecute {
    public static void main(String[] args) {
        Timer timer = new Timer();
        long now = System.currentTimeMillis();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("A run timer=" + System.currentTimeMillis());
            }
        }, new Date(now), 2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("B run timer=" + System.currentTimeMillis());
            }
        }, new Date(now), 2000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("C run timer=" + System.currentTimeMillis());
            }
        }, new Date(now), 2000);

    }
}
