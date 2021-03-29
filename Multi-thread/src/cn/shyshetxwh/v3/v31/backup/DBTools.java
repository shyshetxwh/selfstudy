package cn.shyshetxwh.v3.v31.backup;

/**
 * FileName: DBTools
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 17:05
 */
public class DBTools {
    private volatile boolean prevIsA = false;

    public synchronized void backupA() {
        try {
            while (prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("★★★★★");
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void backupB() {
        try {
            while (!prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("☆☆☆☆☆");
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
