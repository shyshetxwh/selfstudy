package cn.shyshetxwh.v6.v65;

/**
 * FileName: StaticCode
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 16:34
 */

class MyObject {
    private static MyObject instance = null;

    private MyObject() {
    }

    static {
        instance = new MyObject();
    }

    public static MyObject getInstance() {
        return instance;
    }
}

public class StaticCode {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.getInstance().hashCode());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.getInstance().hashCode());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.getInstance().hashCode());
                }
            }
        }).start();
    }
}
