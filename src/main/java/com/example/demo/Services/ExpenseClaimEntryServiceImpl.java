package com.example.demo.Services;


import com.example.demo.DTOs.ExpenseClaimEntryDTO;
import com.example.demo.DTOs.ExpenseClaimEntryMapper;
import com.example.demo.Models.ExpenseClaimEntry;
import com.example.demo.Repositories.ExpenseClaimEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExpenseClaimEntryServiceImpl implements ExpenseClaimEntryService{
    private final ExpenseClaimEntryRepository expenseClaimEntryRepository;
    private final ExpenseClaimEntryMapper expenseClaimEntryMapper;
    private final BaseService baseService;

    public List<ExpenseClaimEntryDTO> getAllExpenseClaimEntrys() {
        return expenseClaimEntryRepository.findAll()
                .stream()
                .map(expenseClaimEntryMapper::toExpenseClaimEntryDTO)
                .collect(Collectors.toList());
    }

    public ExpenseClaimEntryDTO getExpenseClaimEntryById(int id) {
        ExpenseClaimEntry expenseClaimEntry = expenseClaimEntryRepository.findById(id).orElse(null);
        return expenseClaimEntryMapper.toExpenseClaimEntryDTO(expenseClaimEntry);
    }
    public int createExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTOmap) {
        ExpenseClaimEntry expenseClaimEntry = new ExpenseClaimEntry();
        baseService.updateEntity(expenseClaimEntryDTOmap,expenseClaimEntry,ExpenseClaimEntry.class);
        if(expenseClaimEntry.getId() != null && expenseClaimEntryRepository.findById(expenseClaimEntry.getId()).isPresent()) {
            throw new DuplicateKeyException("ExpenseClaimEntry already exists");
        }
        expenseClaimEntryRepository.save(expenseClaimEntry);
        return expenseClaimEntry.getId();
    }
    public void updateExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTOmap, int id) {
        ExpenseClaimEntry expenseClaimEntry = expenseClaimEntryRepository.findById(id).orElseThrow(()->new RuntimeException("ExpenseClaimEntry not found"));
        baseService.updateEntity(expenseClaimEntryDTOmap,expenseClaimEntry,ExpenseClaimEntry.class);
        expenseClaimEntry.setId(id);
        expenseClaimEntryRepository.save(expenseClaimEntry);

    }
    public void deleteExpenseClaimEntry(int id) {
        expenseClaimEntryRepository.deleteById(id);
    }
}
