package cn.shyshetxwh.v7.v75;

/**
 * FileName: ThreadRunSyn
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 20:18
 */

class MyService {
    private ThreadLocal<Integer> printCountLocal = new ThreadLocal<>();
    private static int currentPrintPosition = 0;
    private static int finalPrintPosition = 0;

    public synchronized void printMethod(String eachThreadPrintChar, Integer eachPrintPosition) {
        printCountLocal.set(0);
        while (printCountLocal.get() < 3) {
            if (currentPrintPosition == 3)
                currentPrintPosition = 0;
            while (eachPrintPosition - 1 % 3 != currentPrintPosition) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finalPrintPosition++;
            System.out.println(Thread.currentThread().getName() + "  "
                    + eachThreadPrintChar + " " + "currentPrintPosition=" + currentPrintPosition
                    + " printCountLocal.get()=" + (printCountLocal.get() + 1)
                    + " finalPrintPosition" + finalPrintPosition);
            currentPrintPosition++;
            printCountLocal.set(printCountLocal.get() + 1);
            this.notifyAll();
        }

    }
}

class MyThread extends Thread {
    private MyService service;
    private String eachThreadPrintChar;
    private Integer eachThreadPrintPosition;

    public MyThread(MyService service, String eachThreadPrintChar, Integer eachThreadPrintPosition) {
        this.service = service;
        this.eachThreadPrintChar = eachThreadPrintChar;
        this.eachThreadPrintPosition = eachThreadPrintPosition;
    }

    @Override
    public void run() {
        service.printMethod(eachThreadPrintChar, eachThreadPrintPosition);
    }
}

public class ThreadRunSyn {
    public static void main(String[] args) {
        MyService service = new MyService();

        MyThread a = new MyThread(service, "A", 1);
        a.setName("thread1");
        a.start();

        MyThread b = new MyThread(service, "B", 2);
        b.setName("thread2");
        b.start();

        MyThread c = new MyThread(service, "C", 3);
        c.setName("thread3");
        c.start();
    }
}
