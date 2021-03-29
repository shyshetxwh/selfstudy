package v18.v18serialization;

import cn.shyshetxwh.util.Directory;

import java.io.*;

public class SerialCtrl implements Serializable{
    private String a;
    private transient String b;

    public SerialCtrl(String a, String b) {
        this.a ="Not Transient: "+ a;
        this.b ="Transient: "+b;
    }

    @Override
    public String toString() {
        return a+"\n"+b;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b= (String) stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtrl sc = new SerialCtrl("test1", "test2");
        System.out.println(sc);

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(sc);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtrl sc2 = (SerialCtrl) in.readObject();
        System.out.println(sc2);
    }
}
