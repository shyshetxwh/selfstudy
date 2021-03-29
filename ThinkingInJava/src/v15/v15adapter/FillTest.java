package v15.v15adapter;

import cn.shyshetxwh.util.Generator;
import v15.v15coffeeGenerator.Coffee;
import v15.v15coffeeGenerator.Latte;
import v15.v15coffeeGenerator.Mocha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface Addable<T> {void add(T t); }

class Fill{
    public static <T> void fill(Addable<T> addable,Class<? extends T> cl,int size){
        for (int i = 0; i < size; i++) {
            try {
                addable.add(cl.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

        public static <T> void fill(Addable<T> addable, Generator<T> generator, int size){
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }
}

class AddableCollectionAdapter<T> implements Addable<T>{
    private Collection<T> c;

    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    @Override
    public void add(T t) {
        c.add(t);
    }
}

class Adapter{
    public static <T> Addable<T> collectionAdapter(Collection<T> c){
        return new AddableCollectionAdapter<T>(c);
    }
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T>{
    @Override
    public void add(T t) {
        super.add(t);
    }
}


public class FillTest {
    public static void main(String[] args) {
        List<Coffee> coffees = new ArrayList<>();
        Fill.fill(new AddableCollectionAdapter<Coffee>(coffees),Coffee.class,3);

        Fill.fill(Adapter.collectionAdapter(coffees), Latte.class,2);

        coffees.forEach(System.out::println);

        System.out.println("===============");

        AddableSimpleQueue<Coffee> queue = new AddableSimpleQueue<>();
        Fill.fill(queue, Mocha.class,4);
        Fill.fill(queue, Latte.class,1);

        queue.forEach(System.out::println);
    }

}
