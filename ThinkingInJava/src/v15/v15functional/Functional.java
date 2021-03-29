package v15.v15functional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

interface Combiner<T> { T combine(T x, T y); }
interface UnaryFunctional<R,T> {R functional(T x); }
interface Collector<T> extends UnaryFunctional<T,T>{
    T result();
}
interface UnaryPredicate<T> {boolean test(T x); }

public class Functional {
    public static <T> T reduce(Iterable<T> seq,Combiner<T> combiner){
        Iterator<T> it = seq.iterator();
        if (it.hasNext())
        {
            T result = it.next();
            while (it.hasNext())
                result=combiner.combine(result,it.next());
            return result;
        }
        return null;
    }

    public static <T> Collector<T> forEach(Iterable<T> seq,Collector<T> func){
        for (T t : seq) {
            func.functional(t);
        }
        return func;
    }

    public static <R,T> List<R> transform(Iterable<T> seq,UnaryFunctional<R,T> func){
        List<R> result=new ArrayList<>();
        for (T t : seq) {
            result.add(func.functional(t));
        }
        return result;
    }

    public static <T> List<T> filter(Iterable<T> seq,UnaryPredicate<T> pred){
        List<T> result=new ArrayList<>();
        for (T t : seq) {
            if (pred.test(t))
                result.add(t);
        }
        return result;
    }

    static class IntegerAdder implements Combiner<Integer>{

        @Override
        public Integer combine(Integer x, Integer y) {
            return x+y;
        }
    }

    static class IntegerSubtracter implements Combiner<Integer>{

        @Override
        public Integer combine(Integer x, Integer y) {
            return x-y;
        }
    }

    static class BigDecimalAdder implements Combiner<BigDecimal>{

        @Override
        public BigDecimal combine(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    }

    static class BigIntegerAdder implements Combiner<BigInteger>{


        @Override
        public BigInteger combine(BigInteger x, BigInteger y) {
            return x.add(y);
        }
    }

    static class AtomicLongAdder implements Combiner<AtomicLong>{

        @Override
        public AtomicLong combine(AtomicLong x, AtomicLong y) {
            return new AtomicLong(x.addAndGet(y.get()));
        }
    }

    static class BigDecimalUlp implements UnaryFunctional<BigDecimal,BigDecimal>{

        @Override
        public BigDecimal functional(BigDecimal x) {
            return x.ulp();
        }
    }

    static class GreatThan<T extends Comparable<T>> implements UnaryPredicate<T>{
        private T bound;

        public GreatThan(T bound) {
            this.bound = bound;
        }

        @Override
        public boolean test(T x) {
            return x.compareTo(bound)>0;
        }
    }

    static class MultiplyingIntegerCollector implements Collector<Integer>{
        private Integer val=1;

        @Override
        public Integer functional(Integer x) {
            val *=x;
            return val;
        }

        @Override
        public Integer result() {
            return val;
        }
    }

    public static void main(String[] args) {
        List<Integer> l1= Arrays.asList(1,2,3,4,5,6,7);
        Integer result = reduce(l1, new IntegerAdder());
        System.out.println("result = " + result);

        result=reduce(l1,new IntegerSubtracter());
        System.out.println("result = " + result);

        System.out.println(filter(l1,new GreatThan<Integer>(4)));

        System.out.println(forEach(l1,new MultiplyingIntegerCollector()).result());

        System.out.println(forEach(filter(l1,new GreatThan<Integer>(4)),new MultiplyingIntegerCollector()).result());

        MathContext mc = new MathContext(7);
        List<BigDecimal> lbd=Arrays.asList(
                new BigDecimal(1.1,mc),
                new BigDecimal(2.2,mc),
                new BigDecimal(3.3,mc),
                new BigDecimal(4.4,mc)
        );

        BigDecimal rbd = reduce(lbd, new BigDecimalAdder());
        System.out.println(rbd);

        List<BigInteger> lbi=new ArrayList<>();
        BigInteger bi = BigInteger.valueOf(11);
        for (int i = 0; i < 11; i++) {
            lbi.add(bi);
            bi=bi.nextProbablePrime();
        }
        System.out.println(lbi);

        BigInteger rbi = reduce(lbi, new BigIntegerAdder());
        System.out.println(rbi);
        System.out.println(rbi.isProbablePrime(5));

        List<AtomicLong> lal=Arrays.asList(
                new AtomicLong(11),
                new AtomicLong(47),
                new AtomicLong(74),
                new AtomicLong(133)
        );
        AtomicLong ral = reduce(lal, new AtomicLongAdder());
        System.out.println("ral = " + ral);

        System.out.println(transform(lbd,new BigDecimalUlp()));
    }


}
