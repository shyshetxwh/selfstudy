package cn.shyshetxwh.v5.v51.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: TimerCancel
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 22:48
 */

class MyTask3 extends TimerTask {
    private int i;

    public MyTask3(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i + " not cancel");
    }
}

public class TimerCancel {
    public static void main(String[] args) {
        int i = 0;

        long now = System.currentTimeMillis();

        while (true) {
            i++;
            Timer timer = new Timer();
            MyTask3 task = new MyTask3(i);
            timer.schedule(task, new Date(now));
            timer.cancel();
        }
    }
}
