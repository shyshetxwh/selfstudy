package cn.shyshetxwh.IO.randomAccess;

import cn.shyshetxwh.IO.randomAccess.Employee;

import java.io.*;

public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff=new Employee[3];
        staff[0]=new Employee("张三",75000,1987,12,15);
        staff[1]=new Employee("李四",50000,1989,10,1);
        staff[2]=new Employee("王五",40000,1990,3,15);

        try (DataOutputStream out=new DataOutputStream(new FileOutputStream("employee2.dat"))){
            for (Employee employee : staff) {
                writeData(out,employee);
            }
        }

        try(RandomAccessFile in=new RandomAccessFile("employee2.dat","r"))
        {
            int n=(int)(in.length()/Employee.RECORD_SIZE);
            Employee[] newStaff = new Employee[n];

            for (int i=n-1;i>=0;i--)
            {
                newStaff[i]=new Employee();
                in.seek(i*Employee.RECORD_SIZE);
                newStaff[i]=readData(in);

            }

            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
    }

    private static Employee readData(RandomAccessFile in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y=in.readInt();
        int m=in.readInt();
        int d=in.readInt();
        return new Employee(name,salary,y,m,d);
    }

    private static void writeData(DataOutputStream out, Employee employee) throws IOException {
        DataIO.writeFixedString(employee.getName(),Employee.NAME_SIZE,out);
        out.writeDouble(employee.getSalary());
        out.writeInt(employee.getHireDay().getYear());
        out.writeInt(employee.getHireDay().getMonthValue());
        out.writeInt(employee.getHireDay().getDayOfMonth());

    }


}
