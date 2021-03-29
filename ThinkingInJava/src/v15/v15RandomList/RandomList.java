package v15.v15RandomList;

import java.util.ArrayList;
import java.util.Random;

public class RandomList<T> {
    private ArrayList<T> storage=new ArrayList<>();
    private Random rand=new Random(47);

    public void add(T t)
    {
        storage.add(t);
    }

    public T select()
    {
        return storage.get(rand.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        for (String s : "woo the cat is so cute".split(" ")) {
            randomList.add(s);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(randomList.select());
        }
    }
}
