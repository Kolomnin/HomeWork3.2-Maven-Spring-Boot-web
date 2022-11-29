package com.homework.employee;

import com.homework.employee.model.Employee;
import com.homework.employee.service.DepartmentService;
import com.homework.employee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    private List<Employee> actualEmployee;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 1, 10000);
        Employee employee2 = new Employee("Petr", "Petrov", 2, 20000);
        Employee employee3 = new Employee("Andrey", "Andreev", 3, 25000);

        actualEmployee = List.of(employee1, employee2, employee3);

    }

    @Test
    public void shouldReturnGetEmployeeByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(actualEmployee);
        final int department = 1;
        final List<Employee> actual = actualEmployee.stream().filter(employee -> employee.getDepartment() == department).
                collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeeByDepartment(department);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnGetGroupedEmployeeByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(actualEmployee);
        final Map<Integer, List<Employee>> actual = employeeService.getAllEmployees().stream().collect(Collectors.
                groupingBy(Employee::getDepartment));
        final Map<Integer, List<Employee>> expected = departmentService.getGroupedEmployeeByDepartment();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnGetSumOfSalariesByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(actualEmployee);
        final int department = 1;
        final int actual = (int) actualEmployee.stream().filter(employee -> employee.getDepartment() == department).
                mapToDouble(Employee::getSalary).sum();
        final int expected = departmentService.getSumOfSalariesByDepartment(department);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnGetMinSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(actualEmployee);
        final int department = 1;
        final double actual = actualEmployee.stream().filter(employee -> employee.getDepartment() == department).
                mapToDouble(Employee::getSalary).min().orElseThrow(RuntimeException::new);
        final double expected = departmentService.getMinSalaryByDepartment(department);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnGetMaxSalaryByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(actualEmployee);
        final int department = 1;
        final double actual = actualEmployee.stream().filter(employee -> employee.getDepartment() == department).
                mapToDouble(Employee::getSalary).max().orElseThrow(RuntimeException::new);
        final double expected = departmentService.getMaxSalaryByDepartment(department);
        Assertions.assertEquals(expected, actual);
    }
}
