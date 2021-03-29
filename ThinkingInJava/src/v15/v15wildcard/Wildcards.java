package v15.v15wildcard;

public class Wildcards {
    static void rawArgs(Holder holder,Object arg)
    {
        holder.setValue(arg);
        Object value = holder.getValue();
        System.out.println("value = " + value);
    }

    static void unboundedArgs(Holder<?> holder,Object arg)
    {
        //holder.setValue(arg);
        Object value = holder.getValue();
        System.out.println("value = " + value);
    }

    static <T> T exact(Holder<T> holder,T arg)
    {
        holder.setValue(arg);
        T t = holder.getValue();
        System.out.println("t = " + t);
        return t;
    }

    static <T> T wildSubtype(Holder<? extends T> holder,T arg)
    {
        //holder.setValue(arg);
        T t = holder.getValue();
        System.out.println("t = " + t);
        return t;
    }

    static <T> void wildSupertype(Holder<? super T> holder,T arg)
    {
        holder.setValue(arg);
        Object value = holder.getValue();
        System.out.println("value = " + value);
    }



    public static void main(String[] args) {
        Holder raw = new Holder<Long>();
        raw=new Holder();
        Holder<Long> qualified = new Holder<Long>();
        Holder<?> unbounded=new Holder<Long>();
        Holder<? extends Long> bounded=new Holder<Long>();
        Long lng=1L;

        rawArgs(raw,lng);
        rawArgs(qualified,lng);
        rawArgs(unbounded,lng);
        rawArgs(bounded,lng);

        unboundedArgs(raw,lng);
        unboundedArgs(qualified,lng);
        unboundedArgs(unbounded,lng);
        unboundedArgs(bounded,lng);

        Long r1 = exact(raw, lng);
        Long r2 = exact(qualified, lng);
        //Long r3 = exact(unbounded, lng);
        //Long r4 = exact(bounded, lng);

        Long r5 = wildSubtype(raw, lng);
        Long r6 = wildSubtype(qualified, lng);
        Object r7 = wildSubtype(unbounded, lng);
        Long r8 = wildSubtype(bounded, lng);

        wildSupertype(raw, lng);
        wildSupertype(qualified, lng);
//        wildSupertype(unbounded, lng);
//        wildSupertype(bounded, lng);
    }
}
