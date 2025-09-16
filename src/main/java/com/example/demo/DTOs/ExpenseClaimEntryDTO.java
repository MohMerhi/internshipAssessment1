package com.example.demo.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseClaimEntryDTO {
    private Integer id;
    private LocalDate date;
    private Integer expenseTypeId;
    private String expenseTypeName;
    private Integer expenseClaimId;
    private String description;
    private BigDecimal total;


}
