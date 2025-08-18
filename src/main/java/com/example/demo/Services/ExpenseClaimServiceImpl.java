package com.example.demo.Services;


import com.example.demo.DTOs.ExpenseClaimDTO;
import com.example.demo.DTOs.ExpenseClaimMapper;
import com.example.demo.Models.ExpenseClaim;
import com.example.demo.Repositories.ExpenseClaimEntryRepository;
import com.example.demo.Repositories.ExpenseClaimRepository;
import com.example.demo.exceptions.InvalidRequestDataException;
import com.example.demo.exceptions.RequestValidations;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ExpenseClaimServiceImpl implements ExpenseClaimService{
    private final ExpenseClaimRepository expenseClaimRepository;
    private final ExpenseClaimEntryRepository expenseClaimEntryRepository;
    private final ExpenseClaimMapper expenseClaimMapper;
    private final BaseService baseService;
    private final RequestValidations requestValidations;
    private final ExpenseClaimEntryService expenseClaimEntryService;

    public List<ExpenseClaimDTO> getAllExpenseClaims() {
        List<ExpenseClaimDTO> expenseClaimDTOS = expenseClaimRepository.findAll()
                .stream()
                .map(expenseClaimMapper::toExpenseClaimDTO)
                .collect(Collectors.toList());
        for(ExpenseClaimDTO expense: expenseClaimDTOS){
            expense.setExpenseClaimEntries(expenseClaimEntryService.findEntriesByExpenseClaimId(expense.getId()));
        }
        return expenseClaimDTOS;
    }

    public ExpenseClaimDTO getExpenseClaimById(int id) {
        ExpenseClaim expenseClaim = expenseClaimRepository.findById(id).orElse(null);
        ExpenseClaimDTO expenseClaimDTO =  expenseClaimMapper.toExpenseClaimDTO(expenseClaim);
        expenseClaimDTO.setExpenseClaimEntries(expenseClaimEntryService.findEntriesByExpenseClaimId(expenseClaim.getId()));
        return expenseClaimDTO;
    }
    public int createExpenseClaim(Map<String,Object> expenseClaimDTOmap) {
        List<String> errors = new ArrayList<>();
        requestValidations.validateDate(expenseClaimDTOmap,"date", errors);
        requestValidations.validateName(expenseClaimDTOmap,errors, "status");
        requestValidations.validatePositiveInteger(expenseClaimDTOmap, "employeeId", errors, false);
        if(errors.size() > 0) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        ExpenseClaim expenseClaim = new ExpenseClaim();
        baseService.updateEntity(expenseClaimDTOmap,expenseClaim,ExpenseClaim.class);
        expenseClaim.setTotal(BigDecimal.ZERO);
        if(expenseClaim.getId() != null && expenseClaimRepository.findById(expenseClaim.getId()).isPresent()) {
            throw new DuplicateKeyException("ExpenseClaim already exists");
        }
        expenseClaimRepository.save(expenseClaim);
        return expenseClaim.getId();
    }
    public void updateExpenseClaim(Map<String,Object> expenseClaimDTOmap, int id) {
        List<String> errors = new ArrayList<>();
        requestValidations.validateDate(expenseClaimDTOmap,"date", errors);
        requestValidations.validateName(expenseClaimDTOmap,errors, "status");
        requestValidations.validatePositiveInteger(expenseClaimDTOmap, "employeeId", errors, false);
        if(errors.size() > 0) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        ExpenseClaim expenseClaim = expenseClaimRepository.findById(id).orElseThrow(()->new RuntimeException("ExpenseClaim not found"));
        baseService.updateEntity(expenseClaimDTOmap,expenseClaim,ExpenseClaim.class);
        expenseClaim.setId(id);
        expenseClaimRepository.save(expenseClaim);

    }
    public void deleteExpenseClaim(int id) {
        expenseClaimRepository.deleteById(id);
    }
}
