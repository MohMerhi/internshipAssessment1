package com.example.demo.DTOs;

import com.example.demo.Models.ExpenseType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseTypeMapper {
    ExpenseTypeDTO toExpenseTypeDTO(ExpenseType expenseType);
}
