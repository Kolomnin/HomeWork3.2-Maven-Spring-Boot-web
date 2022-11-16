package com.homework.employee.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Employee {
    private static int counter;
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int department;
    private final int salary;

    public Employee(String firstName, String lastName, int department, int salary) {

        if (StringUtils.isBlank(firstName) && StringUtils.isAlpha(firstName)) {
            this.firstName = StringUtils.capitalize(firstName);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); /** Удобен для обработки базовых ошибок
                                                                       для REST API. Правильно ли я выбрал исключение?*/
        }

        if (StringUtils.isBlank(lastName) && StringUtils.isAlpha(lastName)) {
            this.lastName = StringUtils.capitalize(lastName);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        this.department = department;
        this.salary = salary;
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
