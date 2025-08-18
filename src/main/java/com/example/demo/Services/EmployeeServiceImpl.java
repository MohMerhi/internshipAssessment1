package com.example.demo.Services;


import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.DTOs.EmployeeMapper;
import com.example.demo.Models.Employee;
import com.example.demo.Repositories.EmployeeRepository;
import com.example.demo.exceptions.InvalidRequestDataException;
import com.example.demo.exceptions.RequestValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final BaseService baseService;
    private final RequestValidations requestValidations;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employeeMapper.toEmployeeDTO(employee);
    }
    public int createEmployee(Map<String,Object> employeeDTOmap) {
        List<String> errors = new ArrayList<>();
        requestValidations.validateEmail(employeeDTOmap,errors);
        requestValidations.validateName(employeeDTOmap,errors, "name");
        requestValidations.validatePositiveInteger(employeeDTOmap, "departmentId", errors, false);
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        Employee employee = new Employee();
        baseService.updateEntity(employeeDTOmap,employee,Employee.class);
        employee.setId(null);
        employeeRepository.save(employee);
        return employee.getId();
    }
    public void updateEmployee(Map<String,Object> employeeDTOmap, int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
        List<String> errors = new ArrayList<>();
        requestValidations.validateEmail(employeeDTOmap,errors);
        requestValidations.validateName(employeeDTOmap,errors, "name");
        requestValidations.validatePositiveInteger(employeeDTOmap, "departmentId", errors, false);
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        baseService.updateEntity(employeeDTOmap,employee,Employee.class);
        employeeRepository.save(employee);

    }
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartmentId(int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
