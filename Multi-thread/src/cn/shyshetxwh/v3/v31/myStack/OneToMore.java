package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: OneToMore
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:45
 */
public class OneToMore {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        Producer p = new Producer(stack);

        new ThreadP(p).start();

        for (int i = 0; i < 5; i++) {
            Consumer c = new Consumer(stack);
            new ThreadC(c).start();
        }
    }
}
