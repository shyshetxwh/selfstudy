package cn.shyshetxwh.security.jaas;

import javax.security.auth.callback.*;
import java.io.IOException;

public class SimpleCallbackHandle implements CallbackHandler {
    private String username;
    private char[] password;

    public SimpleCallbackHandle(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback)
            {
                ((NameCallback)callback).setName(username);
            }
            else if (callback instanceof PasswordCallback)
            {
                ((PasswordCallback)callback).setPassword(password);
            }
        }
    }
}
