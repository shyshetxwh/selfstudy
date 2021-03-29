package cn.shyshetxwh.examples;

import java.util.concurrent.*;

/**
 * FileName: Merorizer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 10:38
 */
public class Merorizer<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache
            = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Merorizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
