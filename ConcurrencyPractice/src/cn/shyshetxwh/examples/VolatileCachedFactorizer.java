package cn.shyshetxwh.examples;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * FileName: VolatileCachedFactorizer
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/13 0013 17:53
 */
public class VolatileCachedFactorizer {
    private static BigInteger[] one = {BigInteger.ONE};
    private volatile OneValueCache cache = new OneValueCache(BigInteger.ONE, one);

    public void service(BigInteger i) {
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        System.out.println(Arrays.toString(factors));
    }

    private BigInteger[] factor(BigInteger num) {
        int value = num.intValue();
        if (value == 0) throw new IllegalArgumentException();
        if (value == 1) return new BigInteger[]{BigInteger.ONE};

        ArrayList<Integer> resList = new ArrayList<>();

        for (int i = 1, max = value; i < max; i++) {
            if (value % i == 0) {
                int valuei = value / i;
                resList.add(i);
                if (valuei != i)
                    resList.add(valuei);
                max = valuei;
            }
        }

        BigInteger[] result = new BigInteger[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            Integer integer = resList.get(i);
            String s = String.valueOf(integer);
            BigInteger bigInteger = new BigInteger(s);
            result[i] = bigInteger;
        }
        return result;
    }

    public static void main(String[] args) {
        VolatileCachedFactorizer factorizer = new VolatileCachedFactorizer();
        for (int i = 0; i < 10; i++) {
            final int x = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BigInteger bigInteger = new BigInteger(String.valueOf(x + 100));
                    factorizer.service(bigInteger);
                }
            }).start();
        }
    }
}
