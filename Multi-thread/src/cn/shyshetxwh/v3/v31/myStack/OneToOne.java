package cn.shyshetxwh.v3.v31.myStack;

/**
 * FileName: OneToOne
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 15:37
 */
public class OneToOne {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        Producer p = new Producer(stack);
        Consumer c = new Consumer(stack);

        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);

        threadP.start();
        threadC.start();
    }
}
