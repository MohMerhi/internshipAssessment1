package com.example.demo.Services;

import com.example.demo.DTOs.ExpenseClaimDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseClaimService {
    List<ExpenseClaimDTO> getAllExpenseClaims();
    ExpenseClaimDTO getExpenseClaimById(int id);
    int createExpenseClaim(Map<String,Object> expenseClaimDTOmap);
    void updateExpenseClaim(Map<String,Object> expenseClaimDTO, int id);
    void deleteExpenseClaim(int id);
}
