package v20annotation.v20database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String[] args) throws Exception {
        Class<?> cl = Class.forName("v20annotation.v20database.Member");
        DBTable dbTable = cl.getAnnotation(DBTable.class);
        if (dbTable==null) return;
        String tableName = dbTable.name();
        if (tableName.length()<1) tableName=cl.getName().toUpperCase();
        List<String> columnDefs=new ArrayList<>();
        for (Field field : cl.getDeclaredFields()) {
            String columnName=null;
            Annotation[] anns = field.getDeclaredAnnotations();
            if (anns.length<1) continue;
            if (anns[0] instanceof SQLInteger){
                SQLInteger sInt= (SQLInteger) anns[0];
                if (sInt.name().length()<1) columnName=field.getName().toUpperCase();
                else
                    columnName=sInt.name();
                columnDefs.add(columnName+" INT"+getConstraints(sInt.constraints()));
            }

            if (anns[0] instanceof SQLString){
                SQLString sString= (SQLString) anns[0];
                if (sString.name().length()<1) columnName=field.getName().toUpperCase();
                else columnName=sString.name();
                columnDefs.add(columnName+" VARCHAR("+sString.value()+")"+getConstraints(sString.constraints()));
            }
        }
        StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (String def : columnDefs) {
            createCommand.append("\n    "+def+",");
        }
        String s = createCommand.substring(0, createCommand.length() - 1) + ");";
        System.out.println(s);
    }

    private static String getConstraints(Constraints con){
        String constraints="";
        if (!con.allowNull())
            constraints+=" NOT NULL";
        if (con.primaryKey())
            constraints+=" PRIMARY KEY";
        if(con.unique())
            constraints+=" UNIQUE";
        return constraints;

    }
}
