package cn.shyshetxwh.v4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * FileName: ConnectionDriver
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/10 0010 9:50
 */
public class ConnectionDriver {
    static class ConnectionHandle implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws InterruptedException {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new ConnectionHandle());
    }
}
