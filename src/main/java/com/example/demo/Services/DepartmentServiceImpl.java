package com.example.demo.Services;


import com.example.demo.DTOs.DepartmentDTO;
import com.example.demo.DTOs.DepartmentMapper;
import com.example.demo.Models.Department;
import com.example.demo.Repositories.DepartmentRepository;
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
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final BaseService baseService;
    private final RequestValidations requestValidations;

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDepartmentDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(int id) {
        Department department = departmentRepository.findById(id).orElse(null);
        return departmentMapper.toDepartmentDTO(department);
    }
    public int createDepartment(Map<String,Object> departmentDTOmap) {
        List<String> errors = new ArrayList<>();
        requestValidations.validateName(departmentDTOmap,errors,"name");
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        Department department = new Department();
        baseService.updateEntity(departmentDTOmap,department,Department.class);
        department.setId(null);
        departmentRepository.save(department);
        return department.getId();
    }
    public void updateDepartment(Map<String,Object> departmentDTOmap, int id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
        List<String> errors = new ArrayList<>();
        requestValidations.validateName(departmentDTOmap,errors,"name");
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        baseService.updateEntity(departmentDTOmap,department,Department.class);
        departmentRepository.save(department);

    }
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
