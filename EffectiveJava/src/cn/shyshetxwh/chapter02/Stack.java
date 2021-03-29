package cn.shyshetxwh.chapter02;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * FileName: Stack
 * Author:   admin+shyshetxwh
 * Date:     2021/03/13 22:27
 * <p>
 * 消除过期的对象引用
 */
public class Stack {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
