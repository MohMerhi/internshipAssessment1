package com.example.demo.Services;


import com.example.demo.DTOs.ExpenseClaimDTO;
import com.example.demo.DTOs.ExpenseClaimMapper;
import com.example.demo.Models.ExpenseClaim;
import com.example.demo.Repositories.ExpenseClaimEntryRepository;
import com.example.demo.Repositories.ExpenseClaimRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<ExpenseClaimDTO> getAllExpenseClaims() {
        return expenseClaimRepository.findAll()
                .stream()
                .map(expenseClaimMapper::toExpenseClaimDTO)
                .collect(Collectors.toList());
    }

    public ExpenseClaimDTO getExpenseClaimById(int id) {
        ExpenseClaim expenseClaim = expenseClaimRepository.findById(id).orElse(null);
        return expenseClaimMapper.toExpenseClaimDTO(expenseClaim);
    }
    public int createExpenseClaim(Map<String,Object> expenseClaimDTOmap) {
        ExpenseClaim expenseClaim = new ExpenseClaim();

        baseService.updateEntity(expenseClaimDTOmap,expenseClaim,ExpenseClaim.class);
        expenseClaim.setTotal(BigDecimal.ZERO);
        if(expenseClaim.getId() != null && expenseClaimRepository.findById(expenseClaim.getId()).isPresent()) {
            throw new DuplicateKeyException("ExpenseClaim already exists");
        }
        expenseClaim.setTotal(null);
        expenseClaimRepository.save(expenseClaim);
        return expenseClaim.getId();
    }
    public void updateExpenseClaim(Map<String,Object> expenseClaimDTOmap, int id) {
        ExpenseClaim expenseClaim = expenseClaimRepository.findById(id).orElseThrow(()->new RuntimeException("ExpenseClaim not found"));
        baseService.updateEntity(expenseClaimDTOmap,expenseClaim,ExpenseClaim.class);
        expenseClaim.setId(id);



    }
    public void deleteExpenseClaim(int id) {
        expenseClaimRepository.deleteById(id);
    }
}
