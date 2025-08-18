package com.example.demo.DTOs;

import com.example.demo.Models.ExpenseClaimEntry;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ExpenseClaimDTO {
    private Integer id;
    private LocalDate date;
    private String description;
    private BigDecimal total;
    private String status;
    private Integer employeeId;
    private List<ExpenseClaimEntryDTO> expenseClaimEntries;
}
