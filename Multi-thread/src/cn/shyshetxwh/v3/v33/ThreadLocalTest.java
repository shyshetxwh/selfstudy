package cn.shyshetxwh.v3.v33;

/**
 * FileName: ThreadLocalTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 22:10
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal local = new ThreadLocal();
        local.set("anything");
        System.out.println(local.get());
    }
}
