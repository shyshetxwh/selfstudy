package cn.shyshetxwh.security.jaas;

import sun.security.provider.PolicyParser;

import java.security.Principal;
import java.util.Objects;


/**
 * 主体类包含一个键值对
 * 比如说："role=HR"或者"username=harry"
 */

public class SimplePrincipal implements Principal {
    private String descr;
    private String value;

    public SimplePrincipal(String descr, String value) {
        this.descr = descr;
        this.value = value;
    }

    @Override
    public String getName() {
        return descr+"="+value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass()!= o.getClass())return false;
        SimplePrincipal other = (SimplePrincipal) o;
        return Objects.equals(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
