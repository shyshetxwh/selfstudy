package cn.shyshetxwh.v1;

/**
 * FileName: MyObject
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 9:42
 */
public class MyObject {
    private String username = "1";
    private String password = "11";

    public void setValue(String u, String p) {
        this.username = u;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("停止a线程");
            Thread.currentThread().suspend();
        }
        this.password = p;
    }

    public void printUsernamePassword() {
        System.out.println(username + " " + password);
    }

    public static void main(String[] args) throws InterruptedException {
        MyObject object = new MyObject();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                object.setValue("a", "aa");
            }
        };
        t1.setName("a");
        t1.start();

        Thread.sleep(500);

        Thread t2 = new Thread() {
            @Override
            public void run() {
                object.printUsernamePassword();
            }
        };
        t2.start();
    }
}
