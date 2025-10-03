package com.employee.api.services;

import com.employee.api.dtos.EmployeeDto;
import java.util.List;

// This interface defines what the employee service implementation will have to do
public interface EmployeeService {

    List<EmployeeDto> getAllEmployees(); // retrieves the list of employees 
}
