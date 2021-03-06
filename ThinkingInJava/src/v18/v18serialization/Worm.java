package v18.v18serialization;

import cn.shyshetxwh.util.Directory;

import java.io.*;
import java.util.Random;

class Data implements Serializable{
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private static Random rand=new Random(47);
    private Data[] d={
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10))
    };
    private char c;
    private Worm next;

    public Worm() {
        System.out.println("Default constructor");
    }

    public Worm(int i, char c) {
        System.out.println("Worm constructor: "+i);
        this.c = c;
        if (--i>0)
            next=new Worm(i,(char)(c+1));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data data : d) {
            result.append(data);
        }
        result.append(")");
        if (next!=null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Directory.findCurrentPath("Worm") + "/worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(Directory.findCurrentPath("Worm") + "/worm.out"));
        String s = (String)in.readObject();
        Worm w2 = (Worm) in.readObject();
        System.out.println(s+"w2= "+w2);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
        out2.flush();

        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        s = (String)in2.readObject();
        Worm w3 = (Worm) in2.readObject();
        System.out.println(s+"w3= "+w3);
    }
}
