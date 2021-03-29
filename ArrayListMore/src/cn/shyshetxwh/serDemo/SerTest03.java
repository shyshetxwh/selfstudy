package cn.shyshetxwh.serDemo;

import cn.shyshetxwh.domain.Student;

import java.io.*;
import java.util.ArrayList;

public class SerTest03 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("李四", 19);
        Student s3 = new Student("王五", 20);
        Student s4 = new Student("赵六", 21);
        ArrayList<Student> students=new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        writeObject(students);
        readObject();



    }

    private static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./ArrayListMore/stu.txt"));
        ArrayList<Student> students= (ArrayList<Student>) ois.readObject();
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
