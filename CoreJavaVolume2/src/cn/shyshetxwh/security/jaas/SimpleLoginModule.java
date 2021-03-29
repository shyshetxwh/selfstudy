package cn.shyshetxwh.security.jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import cn.shyshetxwh.security.jaas.SimplePrincipal;

/**
 * 登录模块通过读取一个文件来验证用户
 */

public class SimpleLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String,?>options;


    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject=subject;
        this.callbackHandler=callbackHandler;
        this.options=options;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler==null)throw new LoginException("no handle");
        NameCallback nameCall = new NameCallback("username: ");
        PasswordCallback passwordCall = new PasswordCallback("password", false);
        try {
            callbackHandler.handle(new Callback[]{nameCall,passwordCall});
        } catch (IOException e) {
            LoginException e2 = new LoginException("I/O exception in callback");
            e2.initCause(e);
            throw e2;
        } catch (UnsupportedCallbackException e) {
            LoginException e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        }

        try {
            return checkLogin(nameCall.getName(),passwordCall.getPassword());
        } catch (IOException ex) {
            LoginException ex2 = new LoginException();
            ex2.initCause(ex);
            throw ex2;
        }
    }

    private boolean checkLogin(String username, char[] password) throws IOException {
        try(Scanner in=new Scanner(Paths.get(""+options.get("pwfile")),"utf-8")){
            while(in.hasNextLine())
            {
                String[] inputs = in.nextLine().split("\\|");
                if (inputs[0].equals(username)&& Arrays.equals(inputs[1].toCharArray(),password))
                {
                    String role=inputs[2];
                    Set<Principal> principals = subject.getPrincipals();
                    principals.add(new SimplePrincipal("role",role));
                    principals.add(new SimplePrincipal("username",username));


                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
