package chapter09;

/**
 * FileName: CharTest
 * Author:   admin+shyshetxwh
 * Date:     2021/03/05 23:35
 */
public class CharTest {
    public static void main(String[] args) {
        String name = "aaa_bbb";
        for (int i = 0; i < name.length(); i++) {
            int c = name.codePointAt(i);
            int a = Character.charCount(c);
            System.out.println(i + " " + a);
        }
    }
}
