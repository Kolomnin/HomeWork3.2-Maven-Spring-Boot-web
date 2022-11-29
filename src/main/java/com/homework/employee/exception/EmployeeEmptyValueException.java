package com.homework.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Не все поля заполнены")

public class EmployeeEmptyValueException extends RuntimeException{
}
