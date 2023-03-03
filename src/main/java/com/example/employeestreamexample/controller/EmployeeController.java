package com.example.employeestreamexample.controller;

import com.example.employeestreamexample.model.Employee;
import com.example.employeestreamexample.service.EmployeeBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeBookService service;

    public EmployeeController(EmployeeBookService service) {
        this.service = service;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String middleName, @RequestParam byte department, @RequestParam double salary) {
        return service.addEmployee(lastName, firstName, middleName, department, salary);
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String middleName, @RequestParam byte department, @RequestParam double salary) {
        return service.removeEmployee(lastName, firstName, middleName, department, salary);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String middleName, @RequestParam byte department, @RequestParam double salary) {
        return service.findEmployee(lastName, firstName, middleName, department, salary);
    }
    @GetMapping("/all")
    public Collection printAllEmployees() {
        return service.printAllEmployees();
    }
    @GetMapping("/departments/max-salary")
    public Employee departmentEmployeesMaxSalary(@RequestParam byte departmentNumber) {
        return service.departmentEmployeesMaxSalary(departmentNumber);
    }
    @GetMapping("/departments/min-salary")
    public Employee departmentEmployeesMinSalary(@RequestParam byte departmentNumber) {
        return service.departmentEmployeesMinSalary(departmentNumber);
    }
    @GetMapping("/departments/filter")
    public Employee departmentEmployees(@RequestParam byte departmentNumber) {
        return service.departmentEmployees(departmentNumber);
    }
}
