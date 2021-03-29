package cn.shyshetxwh.IO.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        String patternString="(([1-9]|1[0-2]):([0-5][0-9]))[ap]m";
        Pattern pattern = Pattern.compile(patternString);
        String input="11:59am,12:00pm";
        Matcher matcher = pattern.matcher(input);
        while (matcher.find())
        {
            int groupCount = matcher.groupCount();
            String group = matcher.group();
            for (int i = 1; i <= groupCount; i++) {
                String group_i = matcher.group(i);
                String group_j="("+group_i+")";
                group=group.replace(group_i,group_j);

            }
            System.out.println(group);


        }
    }
}
