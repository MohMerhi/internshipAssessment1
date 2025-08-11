package com.example.demo.DTOs;

import com.example.demo.Models.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);
}
