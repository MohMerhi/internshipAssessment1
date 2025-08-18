package com.example.demo.Services;

import com.example.demo.DTOs.ExpenseClaimEntryDTO;
import com.example.demo.DTOs.SumByEmployeeDTO;
import com.example.demo.DTOs.TypeSumDTO;
import com.example.demo.Models.ExpenseClaimEntry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ExpenseClaimEntryService {
    List<ExpenseClaimEntryDTO> getAllExpenseClaimEntries();
    ExpenseClaimEntryDTO getExpenseClaimEntryById(int id);
    int createExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTOmap);
    void updateExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTO, int id);
    void deleteExpenseClaimEntry(int id);
    void allExpenseClaimEntriesCreation(List<LinkedHashMap<String, Object>> entries, int expenseClaimId);
    List<ExpenseClaimEntryDTO> findByEmployeeAndByType(Map<String,Object> expenseClaimEntryDTOmap);
//    List<ExpenseClaimEntryDTO> findByEmployeeId(Integer employeeId);
    List<TypeSumDTO> findTypeSumByAllTypes();

    List<ExpenseClaimEntryDTO> findEntriesByExpenseClaimId(int expenseClaimId);

    List<SumByEmployeeDTO> getSumByEmployees();
}
