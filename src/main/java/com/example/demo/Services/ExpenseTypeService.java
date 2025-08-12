package com.example.demo.Services;

import com.example.demo.DTOs.ExpenseTypeDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseTypeService {
    List<ExpenseTypeDTO> getAllExpenseTypes();
    ExpenseTypeDTO getExpenseTypeById(int id);
    int createExpenseType(Map<String,Object> expenseTypeDTOmap);
    void updateExpenseType(Map<String,Object> expenseTypeDTO, int id);
    void deleteExpenseType(int id);
}
