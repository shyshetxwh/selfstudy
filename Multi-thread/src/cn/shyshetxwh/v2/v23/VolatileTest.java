package cn.shyshetxwh.v2.v23;

/**
 * FileName: VolatileTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 21:59
 */

class PrintString {
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
}

public class VolatileTest {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("stop print stopThread=" + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
