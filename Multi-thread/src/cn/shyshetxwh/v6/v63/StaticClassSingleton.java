package cn.shyshetxwh.v6.v63;

/**
 * FileName: StaticClassSingleton
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 16:11
 */

class MyObject {
    private static class MyObjectHandler {
        private static MyObject myObject = new MyObject();
    }

    private MyObject() {
    }

    public static MyObject getInstance() {
        return MyObjectHandler.myObject;
    }
}

public class StaticClassSingleton {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(MyObject.getInstance().hashCode());
            }
        }).start();
    }
}
