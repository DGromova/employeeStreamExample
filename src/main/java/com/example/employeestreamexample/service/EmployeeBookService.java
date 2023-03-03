package com.example.employeestreamexample.service;

import com.example.employeestreamexample.model.Employee;

import java.util.Collection;

public interface EmployeeBookService {
    Employee addEmployee(String lastName, String firstName, String middleName, byte department, double salary);
    Employee removeEmployee(String lastName, String firstName, String middleName, byte department, double salary);
    Employee findEmployee(String lastName, String firstName, String middleName, byte department, double salary);
    Collection printAllEmployees();
    Collection departmentEmployeesMinSalary(byte departmentNumber);
    Collection departmentEmployeesMaxSalary(byte departmentNumber);
    Collection departmentEmployees(byte departmentNumber);
}
