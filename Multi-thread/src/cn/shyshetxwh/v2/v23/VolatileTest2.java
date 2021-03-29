package cn.shyshetxwh.v2.v23;

/**
 * FileName: VolatileTest2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:06
 */

class PrintString2 implements Runnable {
    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    public void printStringMethod() {
        try {
            while (isContinuePrint == true) {
                System.out.println("run printStringMethod threadName=" +
                        Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}

public class VolatileTest2 {
    public static void main(String[] args) throws InterruptedException {
        PrintString2 printString2 = new PrintString2();

        new Thread(printString2).start();

        System.out.println("stop print stopThread=" + Thread.currentThread().getName());
        Thread.sleep(200);
        printString2.setContinuePrint(false);
    }
}
