package cn.shyshetxwh.v6.v64;

import java.io.*;

/**
 * FileName: SerializableSingleton
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/8 0008 16:21
 */

class MyObject implements Serializable {
    private static final long serialVersionUID = 888L;
    public static UserInfo userInfo = new UserInfo();
    private static MyObject myObject = new MyObject();

    private MyObject() {
    }

    public static MyObject getInstance() {
        return myObject;
    }

    protected Object readResolve() {
        System.out.println("execute readResolve()");
        return MyObject.myObject;
    }

}

public class SerializableSingleton {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyObject myObject = MyObject.getInstance();
        System.out.println("serializable myObject : " + myObject.hashCode()
                + " userInfo : " + myObject.userInfo.hashCode());
        FileOutputStream fos = new FileOutputStream(new File("myObject.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(myObject);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream(new File("myObject.txt"));
        ObjectInputStream ois = new ObjectInputStream(fis);
        MyObject myObject1 = (MyObject) ois.readObject();
        ois.close();
        fis.close();
        System.out.println("serializable myObject1 :" + myObject1.hashCode()
                + " userInfo : " + myObject1.userInfo.hashCode());
    }
}
