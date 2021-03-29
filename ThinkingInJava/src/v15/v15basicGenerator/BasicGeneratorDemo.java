package v15.v15basicGenerator;

import cn.shyshetxwh.util.BasicGenerator;
import cn.shyshetxwh.util.Generator;

public class BasicGeneratorDemo {
    public static void main(String[] args) {
        Generator<CountObject> gen = BasicGenerator.create(CountObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }
}
