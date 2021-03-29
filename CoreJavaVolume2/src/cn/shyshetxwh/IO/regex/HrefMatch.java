package cn.shyshetxwh.IO.regex;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrefMatch {
    public static void main(String[] args) {
        try {
            String urlString="http://openjdk.java.net/";
            InputStream in = new URL(urlString).openStream();
            int n;
            byte[] bytes = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while((n=in.read(bytes))!=-1)
            {
                String s = new String(bytes, 0, n, StandardCharsets.UTF_8);
                sb.append(s);
            }

            String patternString="<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(sb.toString());
            while(matcher.find())
            {
                String group = matcher.group();
                System.out.println(group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
