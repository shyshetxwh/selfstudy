package cn.shyshetxwh.examples;

/**
 * PseudoRandom
 *
 * @author Brian Goetz and Tim Peierls
 */
public abstract class PseudoRandom {
    int calculateNext(int prev) {
        prev ^= prev << 6;
        prev ^= prev >>> 21;
        prev ^= (prev << 7);
        return prev;
    }

    abstract int nextInt(int n);

}