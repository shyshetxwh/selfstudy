package v15.v15erased;

class Base {}

class Divide extends Base {}

public class ClassTypeCapture<T> {
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg)
    {
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Base> capture1 = new ClassTypeCapture<>(Base.class);
        System.out.println(capture1.f(new Base()));
        System.out.println(capture1.f(new Divide()));

        ClassTypeCapture<Divide> capture2 = new ClassTypeCapture<>(Divide.class);
        System.out.println(capture2.f(new Base()));
        System.out.println(capture2.f(new Divide()));
    }
}
