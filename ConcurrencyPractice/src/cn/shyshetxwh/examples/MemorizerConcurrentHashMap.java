package cn.shyshetxwh.examples;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileName: MemorizerConcurrentHashMap
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 10:13
 */
public class MemorizerConcurrentHashMap<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public MemorizerConcurrentHashMap(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
