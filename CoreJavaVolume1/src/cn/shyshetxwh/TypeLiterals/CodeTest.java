package cn.shyshetxwh.TypeLiterals;

import java.lang.reflect.Type;
import java.util.Random;

public class CodeTest {
    public static void main(String[] args) {
        Type t = new Object() {
        }.getClass();

        System.out.println(t.getTypeName());

    }
}
