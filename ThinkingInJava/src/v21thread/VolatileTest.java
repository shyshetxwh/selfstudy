package v21thread;

abstract class Base{
    protected volatile int a;
}

class Divide1 extends Base{}
class Divide2 extends Base{}

public class VolatileTest {
    public static void main(String[] args) {
        Divide1 d1 = new Divide1();
        Divide2 d2 = new Divide2();

        d1.a=12;

        System.out.println(d1.a);
        System.out.println(d2.a);
    }
}
