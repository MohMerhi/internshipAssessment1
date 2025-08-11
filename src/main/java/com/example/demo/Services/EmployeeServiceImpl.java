package com.example.demo.Services;


import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.DTOs.EmployeeMapper;
import com.example.demo.Models.Employee;
import com.example.demo.Repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final BaseService baseService;

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
        Employee employee = new Employee();
        baseService.updateEntity(employeeDTOmap,employee,Employee.class);
        if(employee.getId() != null && employeeRepository.findById(employee.getId()).isPresent()) {
            throw new DuplicateKeyException("Employee already exists");
        }
        employeeRepository.save(employee);
        return employee.getId();
    }
    public void updateEmployee(Map<String,Object> employeeDTOmap, int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found"));
        baseService.updateEntity(employeeDTOmap,employee,Employee.class);
        employee.setId(id);
        employeeRepository.save(employee);

    }
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartmentName(String name) {
        return employeeRepository.findByDepartmentName(name)
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
