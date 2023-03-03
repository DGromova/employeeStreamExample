package com.example.employeestreamexample.service;

import com.example.employeestreamexample.exception.DepartmentNotFoundException;
import com.example.employeestreamexample.exception.EmployeeAlreadyAddedException;
import com.example.employeestreamexample.exception.EmployeeNotFoundException;
import com.example.employeestreamexample.model.Employee;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeBookServiceIml implements EmployeeBookService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String lastName, String firstName, String middleName, byte department, double salary) {
        Employee employee = new Employee(lastName, firstName, middleName, department, salary);
        if(employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
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
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee findEmployee(String lastName, String firstName, String middleName, byte department, double salary) {
        Employee employee = new Employee(lastName, firstName, middleName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }
    @Override
    public Collection printAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

//    @Override
//    public double salaryMonthsSum() {
//        double salaryMonthsSum = 0;
//        for (Employee employee : employeesMap.values()) {
//            salaryMonthsSum = salaryMonthsSum + employee.getSalary();
//        }
//        System.out.println("Сумма затрат на зарплаты в месяц: " + salaryMonthsSum + " рублей.");
//        return salaryMonthsSum;
//    }
//
//    @Override
//    public void minSalaryEmployee() {
//        Set<Double> actualSalaryList = new HashSet();
//        for (Employee employee : employeesMap.values()) {
//            actualSalaryList.add(employee.getSalary());
//        }
//        double minSalary;
//        minSalary = Collections.min(actualSalaryList);
//        for (Employee employee : employeesMap.values()) {
//            if (employee.getSalary() == minSalary) {
//                System.out.println("Сотрудник с минимальной зарплатой: " + employee);
//            }
//        }
//    }
//
//    @Override
//    public void maxSalaryEmployee() {
//        Set<Double> presentSalaryList = new HashSet();
//        for (Employee employee : employeesMap.values()) {
//            presentSalaryList.add(employee.getSalary());
//        }
//        double maxSalary;
//        maxSalary = Collections.max(presentSalaryList);
//        for (Employee employee : employeesMap.values()) {
//            if (employee.getSalary() == maxSalary) {
//                System.out.println("Сотрудник с максимальной зарплатой: " + employee);
//            }
//        }
//    }
//
//    @Override
//    public void averageMonthsSalary() {
//        System.out.println("Среднее значение зарплат: " + salaryMonthsSum() / (employeesMap.size()) + " рублей.");
//    }
//
//    @Override
//    public void allNames() {
//        System.out.println("Ф.И.О. всех сотрудников:");
//        for (Employee employee : employeesMap.values()) {
//            System.out.println(employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName());
//        }
//    }
//
//    // Методы повышенной сложности
//    @Override
//    public void salaryIndex(double index) {
//        for (Employee employee : employeesMap.values()) {
//            employee.setSalary(employee.getSalary() * index);
//        }
//    }

    @Override
    public Employee departmentEmployeesMinSalary(byte departmentNumber) {
//        Stream employee = employees.entrySet().stream()
//                .filter(employees -> employees.getValue().getDepartment() == departmentNumber)
//                .min(Comparator.comparingInt(employees -> (int) employees.getValue().getSalary())).stream()
//                .;

        Set<Double> actualDepartmentSalaryList = new HashSet();
        for (Employee employee : employees.values()) {
            if (employee.getDepartment() == departmentNumber) {
                actualDepartmentSalaryList.add(employee.getSalary());
            }
        }
        double minSalary;
        minSalary = Collections.min(actualDepartmentSalaryList);
        for (Employee employee : employees.values()) {
            if (employee.getSalary() == minSalary & employee.getDepartment() == departmentNumber) {
                System.out.println(("Сотрудник отдела №" + departmentNumber + " с минимальной зарплатой: " + "id:" + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName() + " " +
                        employee.getMiddleName() + " Зарплата " + employee.getSalary() + " рублей."));
            }
        }
        return null;
    }

    @Override
    public Employee departmentEmployeesMaxSalary(byte departmentNumber) {
        Set<Double> presentDepartmentSalaryList = new HashSet();
        for (Employee employee : employees.values()) {
            if (employee.getDepartment() == departmentNumber) {
                presentDepartmentSalaryList.add(employee.getSalary());
            }
        }
        double maxSalary;
        maxSalary = Collections.max(presentDepartmentSalaryList);
        for (Employee employee : employees.values()) {
            if (employee.getSalary() == maxSalary & employee.getDepartment() == departmentNumber) {
                System.out.println(("Сотрудник отдела №" + departmentNumber + " с максимальной зарплатой: " + "id:" + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName() + " " +
                        employee.getMiddleName() + " Зарплата " + employee.getSalary() + " рублей."));
            }
        }
        return null;
    }

//    @Override
//    public double departmentSalaryMonthsSum(byte departmentNumber) {
//        double departmentSalaryMonthsSum = 0;
//        for (Employee employee : employees.values()) {
//            if (employee.getDepartment() == departmentNumber) {
//                departmentSalaryMonthsSum = departmentSalaryMonthsSum + employee.getSalary();
//            }
//        }
//        System.out.println("Сумма затрат на зарплаты в месяц в отделе №" + departmentNumber + ": " + departmentSalaryMonthsSum + " рублей.");
//        return departmentSalaryMonthsSum;
//    }

//    @Override
//    public void departmentAverageMonthsSalary(byte departmentNumber) {
//        byte x = 0;
//        for (Employee employee : employeesMap.values()) {
//            if (employee.getDepartment() == departmentNumber) {
//                x++;
//            }
//        }
//        System.out.println("Среднее значение зарплат в отделе №" + departmentNumber + ": " + departmentSalaryMonthsSum(departmentNumber) / x + " рублей.");
//    }

    @Override
    public Employee departmentEmployees(byte departmentNumber) {
        employees.entrySet().stream()
                .filter(employees -> employees.getValue().getDepartment() == departmentNumber)
                .forEach(System.out::println);
        return null;
    }
//        System.out.println("Список сотрудников выбранного отдела: ");
//        String result = null;
//        for (Employee employee : employees.values()) {
//            if (employee.getDepartment() == departmentNumber) {
//                result = "EmployeeFound";
//                System.out.println("Сотрудник отдела №" + departmentNumber + ": " + "id:" + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName() + " " +
//                        employee.getMiddleName() + " Зарплата " + employee.getSalary() + " рублей.");
//            }
//        }
//        if (result == null) {
//            throw new DepartmentNotFoundException();
//        }
//    }

//    @Override
//    public void departmentSalaryIndex(byte departmentNumber, double index) {
//        for (Employee employee : employeesMap.values()) {
//            if (employee.getDepartment() == departmentNumber) {
//                employee.setSalary(employee.getSalary() * index);
//            }
//        }
//    }
//
//    @Override
//    public void employeesSalaryLessThan(double number) {
//        System.out.println("Все сотрудники с зарплатой меньше заданного числа: ");
//        for (Employee employee : employeesMap.values()) {
//            if (employee.getSalary() < number) {
//                System.out.println("id:" + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName() + " Зарплата: " + employee.getSalary() + " рублей.");
//            }
//        }
//    }
//
//    @Override
//    public void employeesSalaryMoreThan(double number) {
//        System.out.println("Все сотрудники с зарплатой больше или равной заданному числу: ");
//        for (Employee employee : employeesMap.values()) {
//            if (employee.getSalary() >= number) {
//                System.out.println("id:" + employee.getId() + " " + employee.getLastName() + " " + employee.getFirstName() + " " + employee.getMiddleName() + " Зарплата: " + employee.getSalary() + " рублей.");
//            }
//        }
//    }
}
