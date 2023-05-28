package model;

import annotations.XmlIgnore;
import annotations.XmlName;
import annotations.XmlTypeName;

@XmlTypeName("Человек")
public class Person {
    @XmlName(firstId = "Имя")
    private String firstName="Vasya";

    @XmlName(firstId = "Возраст")
    private double age = 12;

    @XmlIgnore
    private String password;

    public Person() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person(String firstName, double age, String password) {
        this.firstName = firstName;
        this.age = age;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
