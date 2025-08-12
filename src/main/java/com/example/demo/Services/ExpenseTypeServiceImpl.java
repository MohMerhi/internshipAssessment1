package com.example.demo.Services;


import com.example.demo.DTOs.ExpenseTypeDTO;
import com.example.demo.DTOs.ExpenseTypeMapper;
import com.example.demo.Models.ExpenseType;
import com.example.demo.Repositories.ExpenseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService{
    private final ExpenseTypeRepository expenseTypeRepository;
    private final ExpenseTypeMapper expenseTypeMapper;
    private final BaseService baseService;

    public List<ExpenseTypeDTO> getAllExpenseTypes() {
        return expenseTypeRepository.findAll()
                .stream()
                .map(expenseTypeMapper::toExpenseTypeDTO)
                .collect(Collectors.toList());
    }

    public ExpenseTypeDTO getExpenseTypeById(int id) {
        ExpenseType expenseType = expenseTypeRepository.findById(id).orElse(null);
        return expenseTypeMapper.toExpenseTypeDTO(expenseType);
    }
    public int createExpenseType(Map<String,Object> expenseTypeDTOmap) {
        ExpenseType expenseType = new ExpenseType();
        baseService.updateEntity(expenseTypeDTOmap,expenseType,ExpenseType.class);
        if(expenseType.getId() != null && expenseTypeRepository.findById(expenseType.getId()).isPresent()) {
            throw new DuplicateKeyException("ExpenseType already exists");
        }
        expenseTypeRepository.save(expenseType);
        return expenseType.getId();
    }
    public void updateExpenseType(Map<String,Object> expenseTypeDTOmap, int id) {
        ExpenseType expenseType = expenseTypeRepository.findById(id).orElseThrow(()->new RuntimeException("ExpenseType not found"));
        baseService.updateEntity(expenseTypeDTOmap,expenseType,ExpenseType.class);
        expenseType.setId(id);
        expenseTypeRepository.save(expenseType);

    }
    public void deleteExpenseType(int id) {
        expenseTypeRepository.deleteById(id);
    }


}
