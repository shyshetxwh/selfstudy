package cn.shyshetxwh.v3.v34;

/**
 * FileName: UserInfoThreadLocal
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 23:08
 */

class UserInfo {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

class Tools2 {
    public static InheritableThreadLocal<UserInfo> t = new InheritableThreadLocal<>();
}

class Thread2 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                UserInfo userInfo = Tools2.t.get();
                System.out.println("In Thread2 get=" + userInfo.getUsername() +
                        " " + userInfo.hashCode());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class UserInfoThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("china");
        if (Tools2.t.get() == null)
            Tools2.t.set(userInfo);
        System.out.println("    In Main get:" +
                Tools2.t.get().getUsername() + " "
                + Tools2.t.get().hashCode());

        Thread.sleep(100);
        new Thread2().start();

        Thread.sleep(5000);
        Tools2.t.get().setUsername("usa");
    }
}
