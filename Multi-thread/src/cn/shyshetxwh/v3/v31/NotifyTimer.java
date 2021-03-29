package cn.shyshetxwh.v3.v31;

/**
 * FileName: NotifyTimer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 19:35
 */
public class NotifyTimer {
    private String lock = new String("");
    private boolean flag = false;

    private Runnable run1 = new Runnable() {
        @Override
        public void run() {
            //System.out.println(this);
            try {
                synchronized (lock) {
                    while (!flag) {
                        System.out.println("begin wait");
                        lock.wait();
                        System.out.println("end wait");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable run2 = new Runnable() {
        @Override
        public void run() {
            //System.out.println(this);
            synchronized (lock) {
                System.out.println("begin notify");
                lock.notify();
                System.out.println("end notify");
                flag = true;
            }
        }
    };

    public static void main(String[] args) {
        NotifyTimer timer = new NotifyTimer();

        new Thread(timer.run1).start();

        new Thread(timer.run2).start();
    }
}
