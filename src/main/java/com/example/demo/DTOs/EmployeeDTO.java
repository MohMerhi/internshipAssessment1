package com.example.demo.DTOs;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Integer department_id;
}
