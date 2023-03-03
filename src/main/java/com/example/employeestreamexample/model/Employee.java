package com.example.employeestreamexample.model;

import java.util.Objects;

public class Employee {
    private int id;
    static int count = 1;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private byte department;
    private double salary;

    public Employee(String lastName, String firstName, String middleName, byte department, double salary) {
        this.id = count++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.department = department;
        this.salary = salary;
    }
    public int getId() { return id; }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() { return firstName; }

    public String getMiddleName() {
        return middleName;
    }
    public String getFullName() { return lastName + " " + firstName + " " + middleName; }

    public byte getDepartment() {
        return department;
    }


    public double getSalary() {
        return salary;
    }

    public void setDepartment(byte department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        if(salary < 0){
            System.out.println("Salary less than 0");
            return;
        }
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && department == employee.department && Double.compare(employee.salary, salary) == 0 && Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(middleName, employee.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, department, salary);
    }

    @Override
    public String toString() {
        return  " id:" + id + " " + lastName + " " + firstName + " " + middleName + " Отдел: " + department + " Зарплата: " + salary + " рублей.";
    }
}
