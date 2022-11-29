package com.homework.employee;

import com.homework.employee.controller.record.EmployeeRequest;
import com.homework.employee.exception.EmployeeEmptyValueException;
import com.homework.employee.model.Employee;
import com.homework.employee.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private EmployeeService employeeService;
    private List<Employee> employees;


    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("Ivan", "Ivanov", 1, 10000),
                new EmployeeRequest("Petr", "Petrov", 2, 15000),
                new EmployeeRequest("Anatoliy", "Petrov", 3, 20000)
        ).forEach(employeeService::addEmployee);

    }

    @Test
    public void addEmployee() {
        EmployeeRequest request = new EmployeeRequest("Andrey", "Andreev", 1, 10000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        Assertions.assertThat(employeeService.getAllEmployees()).contains(result);
    }

    @Test
    public void getSalarySum() {
        assertEquals(75000, employeeService.getSalarySum());
    }

    @Test
    public void getMinSalary() {
        assertEquals(15000, employeeService.getSalaryMin());
    }

    @Test
    public void getMaxSalary() {
        assertEquals(35000, employeeService.getSalaryMax());
    }

    @Test
    public void getAllEmployeesWithAverageSalary() {
        double averageSal = employees
                .stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow(EmployeeEmptyValueException::new);
        List<Employee> list = employeeService
                .getAllEmployees()
                .stream()
                .filter(e -> e.getSalary() > averageSal)
                .collect(Collectors.toList());
        assertEquals(employeeService.getAllEmployeesWithAverageSalary(), list);
    }
}
