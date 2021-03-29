package v05finalize;

public class Book {
    boolean checkOut=false;

    public Book(boolean checkOut) {
        this.checkOut = checkOut;
    }

    public void checkIn()
    {
        checkOut=false;
    }

    @Override
    protected void finalize() throws Throwable {
        if (checkOut)
        {
            System.out.println("Error: check out");
        }
    }
}
