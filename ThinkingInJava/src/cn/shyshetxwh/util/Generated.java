package cn.shyshetxwh.util;


import java.lang.reflect.Array;

public class Generated {
    /**
     * 填充一个已知数组
     * @param a
     * @param gen
     * @param <T>
     * @return
     */
    public static <T> T[] fill(T[] a,Generator<T> gen){
        return new CollectionData<T>(gen,a.length).toArray(a);
    }

        public static <T> T[] fill(Class<T> type,Generator<T> gen,int size){
            T[] a = (T[]) Array.newInstance(type, size);
            return new CollectionData<T>(gen,size).toArray(a);
        }

}
