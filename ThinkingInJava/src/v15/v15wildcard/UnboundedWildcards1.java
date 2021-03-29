package v15.v15wildcard;

import java.util.ArrayList;
import java.util.List;

public class UnboundedWildcards1 {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;

    static void assign1(List l)
    {
        list1=l;
        list2=l;
        list3=l;
    }

    static void assign2(List<?> l)
    {
        list1=l;
        list2=l;
        list3=l;
    }

    static void assign3(List<? extends Object> l)
    {
        list1=l;
        list2=l;
        list3=l;
    }

    public static void main(String[] args) {
        assign1(new ArrayList());
        assign2(new ArrayList());
        assign3(new ArrayList());

        assign1(new ArrayList<String>());
        assign2(new ArrayList<String>());
        assign3(new ArrayList<String>());
    }
}
