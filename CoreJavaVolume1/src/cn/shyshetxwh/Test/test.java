package cn.shyshetxwh.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    private static final Pattern IMG_PATTERN=Pattern.compile("[<]\\s*([^a])[iI][mM][gG][>]");
    public static void main(String[] args) {


        String page="< bimg>  <  cimg> < aIMG>";
        Matcher matcher = IMG_PATTERN.matcher(page);
        while(matcher.find())
        {
            System.out.println(matcher.start());
            System.out.println(matcher.start(1));
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println(matcher.end());
            System.out.println(matcher.end(1));
        }

    }
}
