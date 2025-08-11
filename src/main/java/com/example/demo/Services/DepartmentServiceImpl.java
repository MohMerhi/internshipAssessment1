package com.example.demo.Services;


import com.example.demo.DTOs.DepartmentDTO;
import com.example.demo.DTOs.DepartmentMapper;
import com.example.demo.Models.Department;
import com.example.demo.Repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final BaseService baseService;

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
        Department department = new Department();
        baseService.updateEntity(departmentDTOmap,department,Department.class);
        if(department.getId() != null && departmentRepository.findById(department.getId()).isPresent()) {
            throw new DuplicateKeyException("Department already exists");
        }
        departmentRepository.save(department);
        return department.getId();
    }
    public void updateDepartment(Map<String,Object> departmentDTOmap, int id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found"));
        baseService.updateEntity(departmentDTOmap,department,Department.class);
        department.setId(id);
        departmentRepository.save(department);

    }
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
