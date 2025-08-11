package com.example.demo.DTOs;

import com.example.demo.Models.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toEmployeeDTO(Employee employee);
}
