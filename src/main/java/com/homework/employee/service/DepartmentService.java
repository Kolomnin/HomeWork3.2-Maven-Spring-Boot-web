package com.homework.employee.service;

import com.homework.employee.model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public int getSumOfSalariesByDepartment(int department) {
        return (int) getEmployeeDepartment(department).mapToDouble(Employee::getSalary).sum();
    }

    public double getMaxSalaryByDepartment(int department) {
        return getEmployeeDepartment(department).mapToDouble(Employee::getSalary).max().orElseThrow(RuntimeException::new);
    }

    public double getMinSalaryByDepartment(int department) {
        return getEmployeeDepartment(department).mapToDouble(Employee::getSalary).min().orElseThrow(RuntimeException::new);
    }

    public List<Employee> getEmployeeByDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment() == department).
                collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getGroupedEmployeeByDepartment() {
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Stream<Employee> getEmployeeDepartment(int department) {
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment() == department);
    }
}


