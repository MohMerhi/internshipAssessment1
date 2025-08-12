package com.example.demo.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseClaimDTO {
    private Integer id;
    private LocalDate date;
    private String description;
    private BigDecimal total;
    private String status;
    private Integer employeeId;
}
