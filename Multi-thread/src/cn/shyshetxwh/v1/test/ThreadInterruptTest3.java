package cn.shyshetxwh.v1.test;

/**
 * FileName: ThreadInterruptTest3
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 21:47
 */
public class ThreadInterruptTest3 {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？=" + Thread.interrupted());
        System.out.println("是否停止2？=" + Thread.interrupted());
        System.out.println("end!");
    }
}
