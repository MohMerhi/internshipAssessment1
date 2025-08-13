package com.example.demo.Controllers;

import com.example.demo.DTOs.ExpenseClaimDTO;
import com.example.demo.Repositories.ExpenseClaimRepository;
import com.example.demo.Services.ExpenseClaimEntryService;
import com.example.demo.Services.ExpenseClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenseClaims")
public class ExpenseClaimController {
    private final ExpenseClaimService expenseClaimService;
    private final ExpenseClaimEntryService expenseClaimEntryService;

    @GetMapping("/{id}")
    public ExpenseClaimDTO getExpenseClaim(@PathVariable int id) {
        return expenseClaimService.getExpenseClaimById(id);
    }


    @GetMapping
    public List<ExpenseClaimDTO> getAllExpenseClaims() {
        return expenseClaimService.getAllExpenseClaims();
    }

    @PostMapping("/create-expenseClaim")
    public int addExpenseClaim(@RequestBody Map<String,Object> expenseClaimDTOmap) {
        return expenseClaimService.createExpenseClaim(expenseClaimDTOmap);

    }
    @PostMapping("/add-full-expense")
    public int addFullExpenseClaim(@RequestBody Map<String,Object> expenseClaimDTOmap){
        int id = expenseClaimService.createExpenseClaim(expenseClaimDTOmap);
        expenseClaimEntryService.allExpenseClaimEntriesCreation((List<LinkedHashMap<String, Object>>) expenseClaimDTOmap.get("entries"),id);
        return id;
    }


    @PatchMapping("/edit-expenseClaim/{id}")
    public void updateExpenseClaim(@PathVariable int id, @RequestBody Map<String,Object> expenseClaimDTOmap) {
        expenseClaimService.updateExpenseClaim(expenseClaimDTOmap,id);
    }

    @DeleteMapping("/delete-expenseClaim/{id}")
    public void deleteExpenseClaim(@PathVariable int id) {
        expenseClaimService.deleteExpenseClaim(id);

    }





}
