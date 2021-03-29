package cn.shyshetxwh.v5.v51.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: DelayExecute
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 22:53
 */
public class DelayExecute {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println("now = " + now);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("A run time " + System.currentTimeMillis());
            }
        }, 7000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("B run time " + System.currentTimeMillis());
            }
        }, 3000, 5000);
    }
}
