package cn.shyshetxwh.TypeLiterals;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Formatter {
    private Map<TypeLiteral<?>,Function<?,String>>rules=new HashMap<>();

    public <T> void forType(TypeLiteral<?>type,Function<T,String>formaterForType)
    {
        rules.put(type,formaterForType);
    }

    public String formatFields(Object obj) throws IllegalAccessException {
        StringBuilder result=new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            result.append(f.getName());
            result.append("=");
            f.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
            if(formatterForType!=null)
            {
                Function<Object,String>objectFormatter=(Function<Object,String>)formatterForType;
                result.append(objectFormatter.apply(f.get(obj)));
            }
            else
            {
                result.append(f.get(obj).toString());

            }
            result.append("\n");


        }
        return result.toString();
    }
}
