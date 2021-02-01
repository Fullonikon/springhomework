package com.netcracker.springhomework.dto;

public class User {
    String lastName;
    String firstName;
    String patronymic;
    int age;
    double salary;
    String mail;
    String workplace;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getMail() {
        return mail;
    }

    public String getWorkplace() {
        return workplace;
    }
}
