package cn.shyshetxwh.chapter04.instrumentedSet;

import java.util.Collection;
import java.util.Set;

/**
 * FileName: InstrumentedSet
 * Author:   admin+shyshetxwh
 * Date:     2021/03/17 22:36
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
