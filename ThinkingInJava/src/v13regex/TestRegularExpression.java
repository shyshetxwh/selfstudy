package v13regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {
    public static void main(String[] args) {
        String input="abcabcabcabcdefabc";
        String regex="(abc)+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(input);
        while(matcher.find())
        {
            System.out.println(matcher.group()+"..."+matcher.start()+"..."+matcher.end());
        }
    }
}
