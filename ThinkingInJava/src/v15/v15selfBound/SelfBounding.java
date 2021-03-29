package v15.v15selfBound;

class SelfBounded<T extends SelfBounded<T>>
{
    T element;

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

}

class A extends SelfBounded<A>{}

class B extends SelfBounded<A>{}

public class SelfBounding {
    public static void main(String[] args) {
        A a = new A();
        a.setElement(a);
        a= a.getElement();

    }
}
