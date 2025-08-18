package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SumByEmployeeDTO {
    private Integer employeeId;
    private BigDecimal total;
}
