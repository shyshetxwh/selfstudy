package v15.v15genericAnonymous;

import cn.shyshetxwh.util.Generator;

public class Teller {
    private static long counter=1;
    private final long id=counter++;
    private Teller(){}

    @Override
    public String toString() {
        return "Teller "+id;
    }

    public static Generator<Teller> generator()
    {
        return new Generator<Teller>() {
            @Override
            public Teller next() {
                return new Teller();
            }
        };
    }
}
