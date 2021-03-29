package cn.shyshetxwh.Net.inetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        String host="www.baidu.com";

        InetAddress[] addresses = InetAddress.getAllByName(host);
        for (InetAddress address : addresses) {
            System.out.println(address);
        }

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }
}
