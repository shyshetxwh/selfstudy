package cn.shyshetxwh.chapter04.superSub;

import java.time.Instant;

/**
 * FileName: Sub
 * Author:   admin+shyshetxwh
 * Date:     2021/03/18 22:16
 */
public final class Sub extends Super {
    private final Instant instant;

    Sub() {
        this.instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
