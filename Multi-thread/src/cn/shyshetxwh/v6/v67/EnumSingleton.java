package cn.shyshetxwh.v6.v67;

/**
 * FileName: EnumSingleton
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 17:03
 */
public class EnumSingleton {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.getConnection().hashCode());
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.getConnection().hashCode());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(MyObject.getConnection().hashCode());
                }
            }
        }).start();
    }
}
