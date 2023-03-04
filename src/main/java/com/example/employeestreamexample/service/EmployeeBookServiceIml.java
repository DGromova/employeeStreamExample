package com.example.employeestreamexample.service;

import com.example.employeestreamexample.exception.EmployeeAlreadyAddedException;
import com.example.employeestreamexample.exception.EmployeeNotFoundException;
import com.example.employeestreamexample.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class EmployeeBookServiceIml implements EmployeeBookService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String lastName, String firstName, String middleName, byte department, double salary) {
        Employee employee = new Employee(lastName, firstName, middleName, department, salary);
        if(employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String lastName, String firstName, String middleName, byte department, double salary) {
        Employee employee = new Employee(lastName, firstName, middleName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    @Override
    public Employee findEmployee(String lastName, String firstName, String middleName, byte department, double salary) {
        Employee employee = new Employee(lastName, firstName, middleName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
    @Override
    public Collection printAllEmployees() {
        return Collections.singleton(employees.values().stream()
                .collect(groupingBy(employees -> employees.getDepartment(), Collectors.toUnmodifiableSet())));
        //Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public Collection departmentEmployeesMinSalary(byte departmentNumber) {
        return employees.values().stream()
                .filter(employees -> employees.getDepartment() == departmentNumber)
                .min(Comparator.comparingDouble(employees -> employees.getSalary()))
                .stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Collection departmentEmployeesMaxSalary(byte departmentNumber) {
        return employees.values().stream()
                .filter(employees -> employees.getDepartment() == departmentNumber)
                .max(Comparator.comparingDouble(employees -> employees.getSalary()))
                .stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Collection departmentEmployees(byte departmentNumber) {
        return employees.values().stream()
                .filter(employees -> employees.getDepartment() == departmentNumber)
                .collect(Collectors.toUnmodifiableSet());
    }
}
