package com.example.employeestreamexample.service;

import com.example.employeestreamexample.model.Employee;

import java.util.Collection;

public interface EmployeeBookService {
    Employee addEmployee(String lastName, String firstName, String middleName, byte department, double salary);

    Employee removeEmployee(String lastName, String firstName, String middleName, byte department, double salary);

    Employee findEmployee(String lastName, String firstName, String middleName, byte department, double salary);
    Collection printAllEmployees();

//    double salaryMonthsSum();
//
//    void minSalaryEmployee();
//
//    void maxSalaryEmployee();
//
//    void averageMonthsSalary();
//
//    void allNames();
//
//    // Методы повышенной сложности
//    void salaryIndex(double index);

    Employee departmentEmployeesMinSalary(byte departmentNumber);

    Employee departmentEmployeesMaxSalary(byte departmentNumber);

//    double departmentSalaryMonthsSum(byte departmentNumber);
//
//    void departmentAverageMonthsSalary(byte departmentNumber);

    Employee departmentEmployees(byte departmentNumber);

//    void departmentSalaryIndex(byte departmentNumber, double index);
//
//    void employeesSalaryLessThan(double number);
//
//    void employeesSalaryMoreThan(double number);

}
