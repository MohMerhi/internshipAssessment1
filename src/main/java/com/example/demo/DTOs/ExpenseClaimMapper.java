package com.example.demo.DTOs;

import com.example.demo.Models.ExpenseClaim;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseClaimMapper {
    ExpenseClaimDTO toExpenseClaimDTO(ExpenseClaim expenseClaim);
}
