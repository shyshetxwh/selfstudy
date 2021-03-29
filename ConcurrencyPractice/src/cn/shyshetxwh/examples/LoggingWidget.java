package cn.shyshetxwh.examples;

/**
 * FileName: LoggingWidget
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 10:07
 */

class Widget {
    public synchronized void doSomething() {
        System.out.println(Thread.currentThread().getName() + "hold lock:" + this);
    }
}

public class LoggingWidget extends Widget {

    @Override
    public synchronized void doSomething() {
        System.out.println(Thread.currentThread().getName() + "hold lock:" + this);
        super.doSomething();
    }

    public static void main(String[] args) {
        LoggingWidget widget = new LoggingWidget();
        widget.doSomething();
    }
}
