package v15.v15wildcard;

public class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<>(new Apple());
        Apple a = apple.getValue();
        apple.setValue(a);

        //Holder<Fruit> fruit=apple;
        Holder<? extends Fruit> fruit=apple;
        Fruit f = fruit.getValue();
        a= (Apple) fruit.getValue();

        try {
            Orange o= (Orange) fruit.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fruit.setValue(new Fruit());
        System.out.println(f.equals(a));
    }
}
