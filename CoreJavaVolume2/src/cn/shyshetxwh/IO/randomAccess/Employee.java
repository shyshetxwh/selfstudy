package cn.shyshetxwh.IO.randomAccess;

import java.time.LocalDate;

public class Employee {

    public static final int NAME_SIZE=40;
    public static final int RECORD_SIZE=2*NAME_SIZE+8+3*4;
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name,double salary,int year,int month,int day)
    {
        this.name=name;
        this.salary=salary;
        this.hireDay=LocalDate.of(year,month,day);
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent)
    {
        double raise = this.salary * byPercent / 100;
        this.salary+=raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
