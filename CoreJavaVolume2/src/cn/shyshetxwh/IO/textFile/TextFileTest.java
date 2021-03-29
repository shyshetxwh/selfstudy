package cn.shyshetxwh.IO.textFile;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0]=new Employee("张三",75000,1987,12,15);
        staff[1]=new Employee("李四",50000,1989,10,1);
        staff[2]=new Employee("王五",40000,1990,3,15);



        try(PrintWriter out=new PrintWriter("employee.dat", "UTF-8"))
        {
            writeData(staff,out);

        }

        try(Scanner in=new Scanner(new FileInputStream("employee.dat"),"utf-8"))
        {
            Employee[] newStaff=readData(in);
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }

    }

    private static Employee[] readData(Scanner in) {
        //先把数组的长度读进来
        int n = in.nextInt();
        //再读一行，跳过第一行数字后面的换行符
        in.nextLine();
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i]=readEmployee(in);
        }
        return employees;
    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        return new Employee(name,salary,hireDate.getYear(),hireDate.getMonthValue(),hireDate.getDayOfMonth());
    }

    private static void writeData(Employee[] staff, PrintWriter out) {
        //先把数组的长度写进去
        out.println(staff.length);
        //再把每个数组元素写进去
        for (Employee employee : staff) {
            writeEmployee(employee,out);
        }
    }

    private static void writeEmployee(Employee employee, PrintWriter out) {
        out.println(employee.getName()+"|"+employee.getSalary()+"|"+employee.getHireDay());
    }
}
