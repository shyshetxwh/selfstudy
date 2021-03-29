package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.LoginServlet;

/**
 * FileName: LoginServletTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/2 0002 17:32
 */
public class LoginServletTest {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                LoginServlet.doPost("a","aa");
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                LoginServlet.doPost("b","bb");
            }
        }.start();
    }
}
