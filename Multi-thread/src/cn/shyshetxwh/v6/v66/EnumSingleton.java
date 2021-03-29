package cn.shyshetxwh.v6.v66;

/**
 * FileName: EnumSingleton
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 16:41
 */


public class EnumSingleton {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.connectionFactory.getConnection().hashCode());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.connectionFactory.getConnection().hashCode());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.connectionFactory.getConnection().hashCode());
                }
            }
        }).start();
    }
}
