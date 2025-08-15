package com.example.demo.Services;


import com.example.demo.DTOs.ExpenseClaimEntryDTO;
import com.example.demo.DTOs.ExpenseClaimEntryMapper;
import com.example.demo.DTOs.TypeSumDTO;
import com.example.demo.Models.ExpenseClaimEntry;
import com.example.demo.Repositories.ExpenseClaimEntryRepository;
import com.example.demo.Repositories.ExpenseClaimRepository;
import com.example.demo.Repositories.ExpenseTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ExpenseClaimEntryServiceImpl implements ExpenseClaimEntryService{
    private final ExpenseClaimEntryRepository expenseClaimEntryRepository;
    private final ExpenseClaimEntryMapper expenseClaimEntryMapper;
    private final BaseService baseService;
    private final ExpenseClaimRepository expenseClaimRepository;
    private final ExpenseTypeRepository expenseTypeRepository;

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
        expenseClaimEntry.setId(null);
        expenseClaimEntryRepository.save(expenseClaimEntry);
        BigDecimal total = expenseClaimEntryRepository.getTotalExpenseClaim(expenseClaimEntry.getExpenseClaimId());
        expenseClaimEntryRepository.updateTotalExpenseClaim(expenseClaimEntry.getExpenseClaimId(), total);
        return expenseClaimEntry.getId();
    }
    public void updateExpenseClaimEntry(Map<String,Object> expenseClaimEntryDTOmap, int id) {
        ExpenseClaimEntry expenseClaimEntry = expenseClaimEntryRepository.findById(id).orElseThrow(()->new RuntimeException("ExpenseClaimEntry not found"));
        baseService.updateEntity(expenseClaimEntryDTOmap,expenseClaimEntry,ExpenseClaimEntry.class);
        expenseClaimEntry.setId(id);
        expenseClaimEntryRepository.save(expenseClaimEntry);
        BigDecimal total = expenseClaimEntryRepository.getTotalExpenseClaim(expenseClaimEntry.getExpenseClaimId());
        expenseClaimEntryRepository.updateTotalExpenseClaim(expenseClaimEntry.getExpenseClaimId(), total);

    }
    public void deleteExpenseClaimEntry(int id) {
        int expenseClaimId = expenseClaimEntryRepository.findById(id).get().getExpenseClaimId();
        expenseClaimEntryRepository.deleteById(id);
        BigDecimal total = expenseClaimEntryRepository.getTotalExpenseClaim(expenseClaimId);
        expenseClaimEntryRepository.updateTotalExpenseClaim(expenseClaimId, total);
    }

    public void allExpenseClaimEntriesCreation(List<LinkedHashMap<String, Object>> entries, int expenseClaimId){
        List<ExpenseClaimEntry> expenseClaimEntries = new ArrayList<>();
        for(LinkedHashMap<String, Object> entry : entries) {
            ExpenseClaimEntry expenseClaimEntry = new ExpenseClaimEntry();
            baseService.updateEntity(entry,expenseClaimEntry,ExpenseClaimEntry.class);
            expenseClaimEntry.setId(null);
            expenseClaimEntry.setExpenseClaimId(expenseClaimId);
            expenseClaimEntries.add(expenseClaimEntry);
        }
        for(ExpenseClaimEntry expenseClaimEntry : expenseClaimEntries) {
            expenseClaimEntryRepository.save(expenseClaimEntry);
        }
        BigDecimal total = expenseClaimEntryRepository.getTotalExpenseClaim(expenseClaimId);
        expenseClaimEntryRepository.updateTotalExpenseClaim(expenseClaimId, total);
    }

    public List<ExpenseClaimEntryDTO> findByEmployeeAndByType(Integer employeeId, Integer typeId){
        return expenseClaimEntryRepository.findAllClaimEntriesByEmployeeIdAndType(employeeId, typeId)
                .stream()
                .map(expenseClaimEntryMapper::toExpenseClaimEntryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypeSumDTO> findTypeSumByAllTypes() {
        return expenseClaimEntryRepository.getTotalGroupedByType();
    }

//    @Override
//    public List<ExpenseClaimEntryDTO> findByEmployeeId(Integer employeeId) {
//        return expenseClaimEntryRepository.findAllByEmployeeId(employeeId)
//                .stream()
//                .map(expenseClaimEntryMapper::toExpenseClaimEntryDTO)
//                .collect(Collectors.toList());
//    }





}
