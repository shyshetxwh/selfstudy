package v15.v15Fibonacci;

import cn.shyshetxwh.util.Generator;

public class Fibonacci implements Generator<Integer> {
    int count=0;

    private int fib(int n)
    {
        if (n<2)return 1;
        return fib(n-2)+fib(n-1);
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        for (int i = 0; i < 18; i++) {
            System.out.print(fib.next()+" ");
        }
    }
}
