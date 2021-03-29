package v20annotation;

import org.junit.Test;

import java.util.HashSet;

public class MyTest {
    HashSet<String> testObject=new HashSet<String>();
    @Test
    public void init(){
        assert !testObject.isEmpty();
    }
}
