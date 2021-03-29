package cn.shyshetxwh.v1.test;

/**
 * FileName: RunTest3
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 16:46
 */
public class RunTest3 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(500000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
