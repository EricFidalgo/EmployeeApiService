package com.employee.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.employee.api.dtos.EmployeeDto;
import com.employee.api.model.Employee;
import com.employee.api.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    // The employee service object is the mediator for CRUD operations for the employees
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET request to call all of the employees from the service layer
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    // GET request to call a single employee by their uuid
    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeDto> getEmployeeByUuid(@PathVariable UUID uuid) {
        // service will return an optional
        Optional<EmployeeDto> employeeDtoOptional = employeeService.getEmployeeByUuid(uuid);

        if (employeeDtoOptional.isPresent()) {
            return ResponseEntity.ok(employeeDtoOptional.get());
        } 
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param requestBody hint!
     * @return Newly created Employee
     */
    public Employee createEmployee(Object requestBody) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
