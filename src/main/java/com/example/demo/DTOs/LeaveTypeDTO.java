package com.example.demo.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LeaveTypeDTO {
    private int id;

    @Size(max = 255)
    @NotBlank(message = "name must be valid")
    private String name;
}
