package cn.shyshetxwh.IO.serialClone;

import cn.shyshetxwh.IO.textFile.Employee;

public class serialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee e1 = new Employee("张三", 35000, 1989, 10, 1);
        Employee e2 = (Employee) e1.clone();
        e1.raiseSalary(10);

        System.out.println(e1);
        System.out.println(e2);
    }
}
