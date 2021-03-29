package cn.shyshetxwh.IO.textFile;

import cn.shyshetxwh.IO.serialClone.SerialCloneable;

import java.time.LocalDate;

public class Employee  extends SerialCloneable{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name,double salary,int year,int month,int day)
    {
        this.name=name;
        this.salary=salary;
        this.hireDay=LocalDate.of(year,month,day);
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
