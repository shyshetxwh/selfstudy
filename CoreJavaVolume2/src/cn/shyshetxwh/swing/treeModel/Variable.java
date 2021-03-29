package cn.shyshetxwh.swing.treeModel;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Variable {
    private Class<?> type;
    private String name;
    private Object value;
    private ArrayList<Field> fields;

    public Variable(Class<?> type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
        fields=new ArrayList<>();

        if (!type.isPrimitive()&&!type.isArray()&&!type.equals(String.class)&&value!=null)
        {
            //把这个类及其所有父类的字段都放入到fields中
            for (Class<?>c=value.getClass();c!=null;c=c.getSuperclass())
            {
                Field[] fs = c.getDeclaredFields();
                AccessibleObject.setAccessible(fs,true);
                for (Field f : fs) {
                    if ((f.getModifiers()& Modifier.STATIC)==0)fields.add(f);
                }
            }
        }
    }

    public Object getValue() {
        return value;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        String r=type+" "+name;
        if (type.isPrimitive())r+="="+value;
        else if (type.equals(String.class))r+="="+value;
        else if (value==null)r+="=null";
        return r;
    }
}
