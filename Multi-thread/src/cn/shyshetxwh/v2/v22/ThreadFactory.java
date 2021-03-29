package cn.shyshetxwh.v2.v22;

/**
 * FileName: ThreadFactory
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 16:23
 */
public class ThreadFactory {
    public Thread thread(String name) {
        return new Thread(new Runnable() {
            private String print = name;

            @Override
            public void run() {
                System.out.println(print);
            }
        }, name);
    }

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactory();
        Thread aaa = factory.thread("aaa");
        aaa.start();
    }
}
