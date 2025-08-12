package com.example.demo.DTOs;

import com.example.demo.Models.ExpenseClaimEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseClaimEntryMapper {
    ExpenseClaimEntryDTO toExpenseClaimEntryDTO(ExpenseClaimEntry expenseClaimEntry);
}
