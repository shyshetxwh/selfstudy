package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: MoreToOne
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:49
 */
public class MoreToOne {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        for (int i = 0; i < 6; i++) {
            Producer p = new Producer(stack);
            new ThreadP(p).start();
        }

        Consumer c = new Consumer(stack);
        new ThreadC(c).start();
    }
}
