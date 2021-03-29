package v05enum;

public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    public void describe()
    {
        System.out.print("This burrito is ");
        switch (degree)
        {
            case NOT:
                System.out.println("not spicy at all.");
                break;
            case MILD:
            case MEDIUM:
                System.out.println("a little hot.");
                break;
            case HOT:
            case FLAMING:
            default:
                System.out.println("maybe too hot.");
        }
    }

    public static void main(String[] args) {
        Burrito plain=new Burrito(Spiciness.NOT);
        plain.describe();
        Burrito greenChile=new Burrito(Spiciness.MILD);
        greenChile.describe();
        Burrito jalapeno=new Burrito(Spiciness.HOT);
        jalapeno.describe();
    }
}
