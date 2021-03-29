package cn.shyshetxwh.v2.v21;

/**
 * FileName: PublicVar
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 17:59
 */

class Thread5 extends Thread {
    private PublicVar publicVar;

    public Thread5(PublicVar publicVar) {
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        publicVar.setValue("B", "BB");
    }
}


public class PublicVar {
    private String username = "A";
    private String password = "AA";

    public synchronized void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name="
                    + Thread.currentThread().getName() + " username="
                    + username + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getValue() {
        System.out.println("getValue method thread name="
                + Thread.currentThread().getName() + " username="
                + username + " password=" + password);
    }

    public static void main(String[] args) throws InterruptedException {
        PublicVar var = new PublicVar();
        Thread5 thread5 = new Thread5(var);
        thread5.start();

        Thread.sleep(200);

        var.getValue();
    }
}
