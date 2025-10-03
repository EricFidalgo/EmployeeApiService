package com.employee.api.mapper;

import com.employee.api.dtos.EmployeeDto;
import com.employee.api.model.Employee;
import org.springframework.stereotype.Component;

// Converts the Employee database entities into Employee Dtos
@Component
public class EmployeeMapper {
    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDto dto = new EmployeeDto();
        dto.setUuid(employee.getUuid());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setFullName(employee.getFullName());
        dto.setJobTitle(employee.getJobTitle());
        dto.setEmail(employee.getEmail());

        return dto;
    }
}