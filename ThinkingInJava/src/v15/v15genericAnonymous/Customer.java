package v15.v15genericAnonymous;

import cn.shyshetxwh.util.Generator;

public class Customer {
    private static long counter=1;
    private final long id=counter++;
    private Customer(){}

    @Override
    public String toString() {
        return "Customer "+id;
    }

    public static Generator<Customer> generator()
    {
        return new Generator<Customer>() {
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }
}
