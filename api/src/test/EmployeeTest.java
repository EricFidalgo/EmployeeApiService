package com.employee.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.api.controller.EmployeeController;
import com.employee.api.dtos.CreateEmployeeRequestDto;
import com.employee.api.dtos.EmployeeDto;
import com.employee.api.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeDto employeeDto;
    private UUID employeeUuid;

    @BeforeEach
    void setUp() {
        employeeUuid = UUID.randomUUID();
        employeeDto = new EmployeeDto();
        employeeDto.setUuid(employeeUuid);
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");
        employeeDto.setFullName("John Doe");
        employeeDto.setEmail("john.doe@example.com");
        employeeDto.setJobTitle("Software Engineer");
    }

    @Test
    void shouldGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of(employeeDto));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value(employeeUuid.toString()))
                .andExpect(jsonPath("$[0].fullName").value("John Doe"));
    }

    @Test
    void shouldGetEmployeeByUuid() throws Exception {
        when(employeeService.getEmployeeByUuid(employeeUuid)).thenReturn(Optional.of(employeeDto));

        mockMvc.perform(get("/api/v1/employees/{uuid}", employeeUuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(employeeUuid.toString()))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldReturnNotFoundForNonExistentEmployee() throws Exception {
        UUID nonExistentUuid = UUID.randomUUID();
        when(employeeService.getEmployeeByUuid(nonExistentUuid)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/employees/{uuid}", nonExistentUuid)).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        CreateEmployeeRequestDto requestDto = new CreateEmployeeRequestDto();
        requestDto.setFirstName("Jane");
        requestDto.setLastName("Doe");
        // Set other required fields for the request DTO...

        when(employeeService.createEmployee(any(CreateEmployeeRequestDto.class))).thenReturn(employeeDto);

        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uuid").exists());
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/v1/employees/{uuid}", employeeUuid)).andExpect(status().isNoContent());
    }
}
