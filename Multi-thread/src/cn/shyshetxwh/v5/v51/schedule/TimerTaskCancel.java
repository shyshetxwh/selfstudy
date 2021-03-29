package cn.shyshetxwh.v5.v51.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FileName: TimerTaskCancel
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 22:36
 */

class MyTaskA extends TimerTask {
    @Override
    public void run() {
        System.out.println("A run timer=" + System.currentTimeMillis());
        this.cancel();
        System.out.println("A cancel itself");
    }
}

class MyTaskB extends TimerTask {
    @Override
    public void run() {
        System.out.println("B run timer=" + System.currentTimeMillis());
    }
}

public class TimerTaskCancel {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        MyTaskA taskA = new MyTaskA();
        MyTaskB taskB = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(taskA, new Date(now), 4000);
        timer.schedule(taskB, new Date(now), 4000);
    }
}
