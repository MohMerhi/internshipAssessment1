package com.example.demo.Services;

import com.example.demo.DTOs.DepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(int id);
    int createDepartment(Map<String,Object> departmentDTOmap);
    void updateDepartment(Map<String,Object> departmentDTO, int id);
    void deleteDepartment(int id);
}
