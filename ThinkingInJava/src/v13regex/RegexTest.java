package v13regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("a+").matcher("aaaabbaaa");
        int i=0;

        while (matcher.find(i))
        {
            System.out.println(matcher.group());
            i++;
        }
    }
}
