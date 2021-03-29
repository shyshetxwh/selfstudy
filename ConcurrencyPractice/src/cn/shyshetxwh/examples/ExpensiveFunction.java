package cn.shyshetxwh.examples;

import java.math.BigInteger;

/**
 * FileName: ExpensiveFunction
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 10:06
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
