package com.example.demo.Controllers;

import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.Repositories.EmployeeRepository;
import com.example.demo.Services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }


    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create-employee")
    public int addEmployee(@RequestBody Map<String,Object> employeeDTOmap) {
        return employeeService.createEmployee(employeeDTOmap);

    }

    @PatchMapping("/edit-employee/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Map<String,Object> employeeDTOmap) {
        employeeService.updateEmployee(employeeDTOmap,id);
    }

    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/department/{department}")
    public List<EmployeeDTO> getEmployeeDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartmentName(department);
    }


}
