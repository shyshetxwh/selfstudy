package v16counting;

import cn.shyshetxwh.util.CountingGenerator;
import cn.shyshetxwh.util.Generator;
import cn.shyshetxwh.util.RandomGenerator;

public class GeneratorsTest {
    public static int size=10;
    public static void test(Class<?> cl){
        for (Class<?> type : cl.getClasses()) {
            System.out.print(type.getSimpleName()+": ");
            try {
                Generator<?> g= (Generator<?>) type.newInstance();
                for (int i = 0; i < size; i++) {
                    System.out.print(g.next()+" ");
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test(CountingGenerator.class);
        System.out.println("==========================");
        test(RandomGenerator.class);
    }
}
