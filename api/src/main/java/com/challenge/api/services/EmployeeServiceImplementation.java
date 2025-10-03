package com.employee.api.services;

import com.employee.api.dtos.EmployeeDto;
import com.employee.api.mapper.EmployeeMapper;
import com.employee.api.model.BasicEmployee;
import com.employee.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// This class contains the business logic operations by separating the implementation from employee service
@Service
public class EmployeeServiceImplementation implements EmployeeService {

    // Handles anything related to the db
    private final EmployeeRepository employeeRepository;

    // The mapper converts between the database entities (BasicEmployee) and Dtos (EmployeeDto)
    private final EmployeeMapper employeeMapper;

    // This constructor uses employeeRepository and employeeMapper to give it the tools it needs
    // employee repository is the repository for accessing employee data
    // employee mapper is the mapper for converting between entity and Dto
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    // Retrieves all employees from the db and converts them to Dtos
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<BasicEmployee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }
}