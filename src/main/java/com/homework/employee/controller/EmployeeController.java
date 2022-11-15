package com.homework.employee.controller;

import com.homework.employee.controller.record.EmployeeRequest;
import com.homework.employee.model.Employee;
import com.homework.employee.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * HTTP методы
 * GET - получение ресурса или набора ресурсов
 * POST - создание ресурза
 * PUT - модификация ресурса
 * PATCH - частичная модификация ресурса
 * DELETE - удаление ресерса
 */


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {
        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public int getSalaryMin() {
        return this.employeeService.getSalaryMin();
    }

    @GetMapping("/employees/salary/max")
    public int getSalaryMax() {
        return this.employeeService.getSalaryMax();
    }

    @GetMapping("/employees/salary/average")
    public int getSalaryAverage() {
        return this.employeeService.getSalaryAverage();
    }

    @GetMapping("/employees/salary/highAverage")
    public List<Employee> getSalaryHighAverage() {
        return this.employeeService.getSalaryHighAverage();
    }
}
