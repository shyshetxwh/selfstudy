package cn.shyshetxwh.IO.serialClone;

import java.io.*;

public class SerialCloneable implements Cloneable,Serializable {
    @Override
    public Object clone() throws CloneNotSupportedException {
//        Object object=null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out=new ObjectOutputStream(bout)){
                out.writeObject(this);
            }

            byte[] bytes = bout.toByteArray();
            ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
            try (ObjectInputStream in=new ObjectInputStream(bin)){
                Object object= in.readObject();
                return object;

            }
        } catch (IOException|ClassNotFoundException e) {
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }

    }
}
