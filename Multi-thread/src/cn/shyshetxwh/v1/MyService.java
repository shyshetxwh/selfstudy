package cn.shyshetxwh.v1;

/**
 * FileName: MyService
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:12
 */
public class MyService {
    private String username = "a";
    private String password = "aa";

    public synchronized String getUsername() {
        return username;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void printString(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(100000000);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
