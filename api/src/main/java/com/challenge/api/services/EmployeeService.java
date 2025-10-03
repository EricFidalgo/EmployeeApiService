package com.employee.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.employee.api.dtos.EmployeeDto;

// This interface defines what the employee service implementation will have to do
public interface EmployeeService {

    List<EmployeeDto> getAllEmployees(); // retrieves the list of employees 

    Optional<EmployeeDto> getEmployeeByUuid(UUID uuid); // retrieves the specific employee, if uuid is wrong it will return an empty optional
}
