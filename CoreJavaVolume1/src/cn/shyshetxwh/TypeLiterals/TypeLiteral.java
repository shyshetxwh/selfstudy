package cn.shyshetxwh.TypeLiterals;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

class TypeLiteral<T> {
    private Type type;

    public TypeLiteral()
    {
        Type parentType = getClass().getGenericSuperclass();
        if(parentType instanceof ParameterizedType)
        {
            type=((ParameterizedType)parentType).getActualTypeArguments()[0];
        }
        else
        {
            throw new UnsupportedOperationException("Construct as new TypeLiteral<...>(){}");
        }

    }

    private TypeLiteral(Type type)
    {
        this.type=type;
    }

    public static TypeLiteral<?> of(Type type)
    {
        return new TypeLiteral<Object>(type);
    }

    @Override
    public String toString() {
        if(type instanceof Class)
        {
            return ((Class<?>)type).getName();
        }
        else
        {
            return type.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
       return o instanceof TypeLiteral
               && type.equals(((TypeLiteral<?>)o).type);
    }

    @Override
    public int hashCode() {

        return type.hashCode();
    }
}
