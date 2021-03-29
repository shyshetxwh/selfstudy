package cn.shyshetxwh.chapter06.item39;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionRepeatTest {
    Class<? extends Exception> value();
}
