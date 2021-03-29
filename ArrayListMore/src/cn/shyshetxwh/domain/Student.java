package cn.shyshetxwh.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private Integer age;


    /*
    *
    * return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';*/
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Student{name='");
        builder.append(this.name);
        builder.append("', age=");
        builder.append(this.age);
        builder.append("}");
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student(String name, Integer age) {

        this.name = name;
        this.age = age;
    }

    public Student() {

    }
}
