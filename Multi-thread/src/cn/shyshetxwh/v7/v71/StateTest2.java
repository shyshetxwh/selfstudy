package cn.shyshetxwh.v7.v71;

/**
 * FileName: StateTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 19:14
 */

public class StateTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("begin sleep");
                    Thread.sleep(10000);
                    System.out.println("  end sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(1000);
        System.out.println("In main t state: " + t.getState());
    }
}
