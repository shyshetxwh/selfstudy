package cn.shyshetxwh.serDemo;

import cn.shyshetxwh.domain.Student;

import java.io.*;

public class SerTest01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        writeObject();

        readObject();
    }

    private static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./ArrayListMore/obj.txt"));
        Student stu = (Student) ois.readObject();
        ois.close();
        System.out.println(stu);
    }

    private static void writeObject() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./ArrayListMore/obj.txt"));
        Student s = new Student("张三", 32);
        oos.writeObject(s);
        oos.close();
    }
}
