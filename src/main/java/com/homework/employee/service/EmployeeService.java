package com.homework.employee.service;

import com.homework.employee.model.Employee;
import com.homework.employee.controller.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should by set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getFirstName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees
                .values()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getSalaryMin() {
        return employees
                .values()
                .stream()
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(() -> new RuntimeException("Something went wrong"));
    }

    public int getSalaryMax() {
        return employees
                .values()
                .stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(() -> new RuntimeException("Something went wrong"));
    }

    public int getSalaryAverage() {
        return (int) employees
                .values()
                .stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow(() -> new RuntimeException("Something went wrong"));
    }

    public List<Employee> getSalaryHighAverage() {
        return employees
                .values()
                .stream()
                .filter(employee -> getSalaryAverage() < employee.getSalary())
                .collect(Collectors.toList());
    }
}
