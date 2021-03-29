package v18.v18serialization;

import cn.shyshetxwh.util.Directory;

import java.io.*;

public class Blip implements Externalizable{
    private int n;
    private String s;

    public Blip() {
        System.out.println("Default constructor");
    }

    public Blip(int n, String s) {
        System.out.println("Blip(int n, String s)");
        this.n = n;
        this.s = s;
    }

    @Override
    public String toString() {
        return s+n;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip.writeExternal");
        out.writeObject(s);
        out.writeInt(n);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip.readExternal");
        s= (String) in.readObject();
        n=in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path= Directory.findCurrentPath("Blip")+"/blip.out";
        Blip blip = new Blip(47,"A String");
        System.out.println(blip);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(blip);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        blip= (Blip) in.readObject();
        System.out.println(blip);
    }
}
