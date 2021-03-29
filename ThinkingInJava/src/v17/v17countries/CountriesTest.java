package v17.v17countries;

import cn.shyshetxwh.util.Countries;

import java.util.*;

public class CountriesTest {
    public static void main(String[] args) {
        System.out.println(Countries.capitals(10));
        System.out.println(Countries.names(10));

        System.out.println(new HashMap<String,String>(Countries.capitals(3)));
        System.out.println(new LinkedHashMap<String,String>(Countries.capitals(3)));
        System.out.println(new TreeMap<String,String>(Countries.capitals(3)));
        System.out.println(new Hashtable<String,String>(Countries.capitals(3)));
        System.out.println(new HashSet<String>(Countries.names(6)));
        System.out.println(new LinkedHashSet<String>(Countries.names(6)));
        System.out.println(new TreeSet<String>(Countries.names(6)));
        System.out.println(new ArrayList<String>(Countries.names(6)));
        System.out.println(new LinkedList<String>(Countries.names(6)));
        System.out.println(Countries.capitals().get("CHINA"));

    }
}
