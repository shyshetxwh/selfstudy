package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: Consumer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:34
 */
public class Consumer {
    private MyStack stack;

    public Consumer(MyStack stack) {
        this.stack = stack;
    }

    public void popService() {
        System.out.println("pop=" + stack.pop());
    }
}
