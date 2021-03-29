package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: Producer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:33
 */
public class Producer {
    private MyStack stack;

    public Producer(MyStack stack) {
        this.stack = stack;
    }

    public void pushService() {
        stack.push();
    }
}
