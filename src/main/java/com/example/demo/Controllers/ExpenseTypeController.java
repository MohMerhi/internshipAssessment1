package com.example.demo.Controllers;

import com.example.demo.DTOs.ExpenseTypeDTO;
import com.example.demo.Repositories.ExpenseTypeRepository;
import com.example.demo.Services.ExpenseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenseTypes")
public class ExpenseTypeController {
    private final ExpenseTypeService expenseTypeService;

    @GetMapping("/{id}")
    public ExpenseTypeDTO getExpenseType(@PathVariable int id) {
        return expenseTypeService.getExpenseTypeById(id);
    }


    @GetMapping
    public List<ExpenseTypeDTO> getAllExpenseTypes() {
        return expenseTypeService.getAllExpenseTypes();
    }

    @PostMapping("/create-expenseType")
    public int addExpenseType(@RequestBody Map<String,Object> expenseTypeDTOmap) {
        return expenseTypeService.createExpenseType(expenseTypeDTOmap);

    }

    @PatchMapping("/edit-expenseType/{id}")
    public void updateExpenseType(@PathVariable int id, @RequestBody Map<String,Object> expenseTypeDTOmap) {
        expenseTypeService.updateExpenseType(expenseTypeDTOmap,id);
    }

    @DeleteMapping("/delete-expenseType/{id}")
    public void deleteExpenseType(@PathVariable int id) {
        expenseTypeService.deleteExpenseType(id);
    }



}
