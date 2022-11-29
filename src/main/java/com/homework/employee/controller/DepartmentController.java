package com.homework.employee.controller;

import com.homework.employee.model.Employee;
import com.homework.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/sum")
    public int getSumOfSalariesByDepartment(@PathVariable("id") int id) {
        return departmentService.getSumOfSalariesByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public double getMaxSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getMaxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public double getMinSalaryByDepartment(@PathVariable("id") int id) {
        return departmentService.getMinSalaryByDepartment(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeByDepartment(@PathVariable("id") int id) {
        return departmentService.getEmployeeByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getGroupedEmployeeByDepartment() {
        return departmentService.getGroupedEmployeeByDepartment();
    }
}
