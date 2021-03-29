package cn.shyshetxwh.Net.post;


import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;

import java.io.PrintWriter;
import java.net.*;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class PostTest {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        try(InputStream in = PostTest.class.getClassLoader().getResourceAsStream("post.properties")){
            props.load(in);
        }
        String urlString = props.remove("url").toString();
        Object userAgent = props.remove("User-Agent");
        Object redirects = props.remove("redirects");
        //设置全局cookie
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String result=doPost(new URL(urlString),props,
                userAgent==null?null:userAgent.toString(),
                redirects==null?-1:Integer.parseInt(redirects.toString()));
        System.out.println("result = " + result);


    }

    private static String doPost(URL url, Map<Object, Object> props, String userAgent, int redirects) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (userAgent!=null)
        {
            //替换掉Java字符串，否则网址不响应
            connection.setRequestProperty("User-Agent",userAgent);
        }
        if (redirects>=0)
        {
            //关闭自动重定向
            connection.setInstanceFollowRedirects(false);
        }
        //打开输出流，可以向连接添加请求头
        connection.setDoOutput(true);

        try(PrintWriter out=new PrintWriter(connection.getOutputStream())){
            boolean first=true;
            for (Map.Entry<Object, Object> pair : props.entrySet()) {
                if (first)
                {
                    first=false;
                }
                else
                {
                    out.print('&');
                }
                String name = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(name);
                out.print('=');
                out.print(URLEncoder.encode(value, "utf-8"));
            }
        }
        String encoding = connection.getContentEncoding();
        if (encoding==null)
        {
            encoding="utf-8";
        }

        if (redirects>0)
        {
            int responseCode = connection.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_MOVED_PERM||
                    responseCode==HttpURLConnection.HTTP_MOVED_TEMP||
                    responseCode==HttpURLConnection.HTTP_SEE_OTHER)
            {
                String location = connection.getHeaderField("Location");
                if (location!=null)
                {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base,location),props,userAgent,redirects-1);
                }
            }
        }
        else if (redirects==0)
        {
            throw new IOException("Too Many redirects");
        }

        StringBuilder response = new StringBuilder();
        try(Scanner in=new Scanner(connection.getInputStream(),encoding)){
            while(in.hasNextLine())
            {
                response.append(in.nextLine());
                response.append("\n");
            }
        }
        catch (IOException e)
        {
            InputStream err = connection.getErrorStream();
            if (err==null)
            {
                throw e;
            }
            try(Scanner in=new Scanner(err)){
                response.append(in.nextLine());
                response.append("\n");
            }
        }
        return response.toString();
    }
}
