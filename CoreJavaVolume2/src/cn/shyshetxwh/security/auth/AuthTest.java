package cn.shyshetxwh.security.auth;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;



public class AuthTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy","AuthTest.policy");
        System.setProperty("java.security.auth.login.config","jaas.config");
        System.setSecurityManager(new SecurityManager());
        try {
            LoginContext context = new LoginContext("Login1");
            context.login();
            System.out.println("Authentication successful");
            Subject subject = context.getSubject();
            System.out.println("subject = " + subject);
            SysPropAction action = new SysPropAction("user.home");
            String result = Subject.doAsPrivileged(subject, action, null);
            System.out.println(result);
            context.logout();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
