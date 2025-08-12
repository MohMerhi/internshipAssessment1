package com.example.demo.Services;

import com.example.demo.DTOs.ExpenseClaimEntryDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseClaimEntryService {
    List<ExpenseClaimEntryDTO> getAllExpenseClaimEntrys();
    ExpenseClaimEntryDTO getExpenseClaimEntryById(int id);
    int createExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTOmap);
    void updateExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTO, int id);
    void deleteExpenseClaimEntry(int id);
}
