package com.example.demo.Services;

import com.example.demo.DTOs.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(int id);
    int createEmployee(Map<String,Object> employeeDTOmap);
    void updateEmployee(Map<String,Object> employeeDTO, int id);
    void deleteEmployee(int id);
    List<EmployeeDTO> getEmployeesByDepartmentName(String name);
}
