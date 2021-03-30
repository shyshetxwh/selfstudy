package cn.shyshetxwh.chapter07.item45;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;


/**
 * FileName: MersennePrimes
 * Author:   admin+shyshetxwh
 * Date:     2021/03/30 19:13
 */
public class MersennePrimes {
    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
    }

    public static void main(String[] args) {
        primes().map(p -> BigInteger.valueOf(2).pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }
}
