package cn.shyshetxwh.v1;

/**
 * FileName: LoginServlet
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:29
 */
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;

    public static /*synchronized*/ void doPost(String username, String password) {
        try {
            usernameRef = username;
            if (username.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username= " + usernameRef + " password=" + passwordRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
