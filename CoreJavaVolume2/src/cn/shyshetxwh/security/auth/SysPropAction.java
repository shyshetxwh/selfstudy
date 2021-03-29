package cn.shyshetxwh.security.auth;

import java.security.PrivilegedAction;

public class SysPropAction implements PrivilegedAction<String> {
    private String propertyName;

    public SysPropAction(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String run() {
        return System.getProperty(propertyName);
    }
}
