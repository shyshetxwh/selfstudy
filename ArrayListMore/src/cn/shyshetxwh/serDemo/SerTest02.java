package cn.shyshetxwh.serDemo;

import cn.shyshetxwh.domain.Student;

import java.io.*;

public class SerTest02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("李四", 19);
        Student s3 = new Student("王五", 20);
        Student s4 = new Student("赵六", 21);
        Student[] students = {s1,s2,s3,s4};
        writeObject(students);
        readObject();



    }

    private static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./ArrayListMore/stu.txt"));
        Student[] students= (Student[]) ois.readObject();
        ois.close();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void writeObject(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./ArrayListMore/stu.txt"));
        //Student s = new Student("张三", 32);
        oos.writeObject(obj);
        oos.close();
    }
}
