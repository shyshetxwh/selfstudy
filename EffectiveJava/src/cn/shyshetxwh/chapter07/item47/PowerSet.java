package cn.shyshetxwh.chapter07.item47;

import java.util.*;

/**
 * FileName: PowerSet
 * Author:   admin+shyshetxwh
 * Date:     2021/03/30 19:20
 */
public class PowerSet {
    public static final <E> Collection<Set<E>> of(Set<E> s) {
        List<E> src = new ArrayList<>(s);
        if (src.size() > 30)
            throw new IllegalArgumentException("Set too big " + s);
        return new AbstractList<Set<E>>() {
            @Override
            public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>= 1) {
                    if ((index & 1) == 1)
                        result.add(src.get(i));
                }
                return result;
            }

            @Override
            public int size() {
                return 1 << src.size();
            }

            @Override
            public boolean contains(Object o) {
                return o instanceof Set && src.containsAll((Set) o);
            }
        };
    }

    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, "a", "b", "c");
        Collection<Set<String>> sets = of(s);
        for (Set<String> set : sets) {
            System.out.println(set);
        }
    }
}
