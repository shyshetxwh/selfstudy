package cn.shyshetxwh.Thread.completableFuture;

import java.net.MalformedURLException;
import java.net.URL;

public class CompletableFutureTest {
    public static void main(String[] args) throws MalformedURLException {
        new CompletableFutureDemo().run(new URL("https://www.ly.com/"));
    }
}
