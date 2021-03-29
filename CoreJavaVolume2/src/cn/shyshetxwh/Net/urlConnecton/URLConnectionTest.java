package cn.shyshetxwh.Net.urlConnecton;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName="https://www.baidu.com";
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();
            connection.connect();
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                for (String value : entry.getValue()) {
                    System.out.println(key+": "+value);
                }
            }

            System.out.println("---------------");
            String contentType = connection.getContentType();
            System.out.println("contentType = " + contentType);
            int contentLength = connection.getContentLength();
            System.out.println("contentLength = " + contentLength);
            String contentEncoding = connection.getContentEncoding();
            System.out.println("contentEncoding = " + contentEncoding);
            long date = connection.getDate();
            System.out.println("date = " + date);
            long expiration = connection.getExpiration();
            System.out.println("expiration = " + expiration);
            long lastModified = connection.getLastModified();
            System.out.println("lastModified = " + lastModified);

            if (contentEncoding==null)
            {
                contentEncoding="utf-8";
            }
            try(Scanner in=new Scanner(connection.getInputStream(),contentEncoding)){
                for (int i = 1; i <=10&&in.hasNextLine() ; i++) {
                    System.out.println(in.nextLine());

                }
                if (in.hasNextLine())
                {
                    System.out.println("...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
