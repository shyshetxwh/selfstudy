package cn.shyshetxwh.chapter02;

/**
 * FileName: Sum
 * Author:   admin+shyshetxwh
 * Date:     2021/03/12 23:38
 */
public class Sum {
    private static long sum() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }

    private static long sum1() {
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }


    public static void main(String[] args) {
        int numSets = Integer.parseInt(args[0]);
        long x = 0;
        long x1 = 0;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            x += sum();
            long end = System.nanoTime();
            System.out.println((end - start) / 1_000_000. + " ms.");
        }

        // Prevents VM from optimizing away everything.
        if (x == 42)
            System.out.println();

        System.out.println("===========================");
        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            x1 += sum1();
            long end = System.nanoTime();
            System.out.println((end - start) / 1_000_000. + " ms.");
        }

        // Prevents VM from optimizing away everything.
        if (x1 == 42)
            System.out.println();
    }
}
