package cn.shyshetxwh.chapter07.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * FileName: ParallelPrimeCounting
 * Author:   admin+shyshetxwh
 * Date:     2021/03/30 19:46
 */
public class ParallelPrimeCounting {
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static void main(String[] args) {
        System.out.println(pi(100000000));
    }
}
