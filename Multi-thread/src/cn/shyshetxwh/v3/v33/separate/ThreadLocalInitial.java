package cn.shyshetxwh.v3.v33.separate;

/**
 * FileName: ThreadLocalInitial
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 22:35
 */

class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "default";
    }
}

public class ThreadLocalInitial {
    private static ThreadLocalExt ext = new ThreadLocalExt();

    public static void main(String[] args) {
        if (ext.get() == null) {
            System.out.println("never set");
            ext.set("new");
        }
        System.out.println(ext.get());
        System.out.println(ext.get());
    }
}
