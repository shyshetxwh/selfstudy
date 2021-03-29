package v15.v15basicGenerator;

public class CountObject {
    private static long counter=0;
    private final long id=counter++;
    public long id() {return id;}

    @Override
    public String toString() {
        return "CountObject "+id;
    }
}
