package cn.shyshetxwh.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class test2 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://www.itcast.cn/index.html");
        String img="http://www.itcast.cn/images/close.png";
        URL url1 = new URL(url, img);
        System.out.println(url1.toString());
    }
}
