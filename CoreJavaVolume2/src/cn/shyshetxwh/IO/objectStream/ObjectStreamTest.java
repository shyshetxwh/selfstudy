package cn.shyshetxwh.IO.objectStream;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee e1 = new Employee("张三", 50000, 1989, 10, 1);
        Manager m1 = new Manager("李四", 80000, 1987, 12, 15);
        m1.setSecretary(e1);
        Manager m2 = new Manager("王五", 40000, 1990, 3, 15);
        m2.setSecretary(e1);

        Employee[] staff = new Employee[3];
        staff[0]=e1;
        staff[1]=m1;
        staff[2]=m2;

        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("employee3.dat"))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in=new ObjectInputStream(new FileInputStream("employee3.dat"))){
            Employee[] newStaff = (Employee[]) in.readObject();

            newStaff[1].raiseSalary(10);

            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
    }
}
