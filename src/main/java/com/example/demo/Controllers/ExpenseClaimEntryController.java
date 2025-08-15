package com.example.demo.Controllers;

import com.example.demo.DTOs.ExpenseClaimEntryDTO;
import com.example.demo.DTOs.TypeSumDTO;
import com.example.demo.Repositories.ExpenseClaimEntryRepository;
import com.example.demo.Services.ExpenseClaimEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenseClaimEntrys")
public class ExpenseClaimEntryController {
    private final ExpenseClaimEntryService expenseClaimEntryService;

    @GetMapping("/{id}")
    public ExpenseClaimEntryDTO getExpenseClaimEntry(@PathVariable int id) {
        return expenseClaimEntryService.getExpenseClaimEntryById(id);
    }


    @GetMapping
    public List<ExpenseClaimEntryDTO> getAllExpenseClaimEntrys() {
        return expenseClaimEntryService.getAllExpenseClaimEntrys();
    }

    @PostMapping("/create-expenseClaimEntry")
    public int addExpenseClaimEntry(@RequestBody Map<String,Object> expenseClaimEntryDTOmap) {
        return expenseClaimEntryService.createExpenseClaimEntry(expenseClaimEntryDTOmap);

    }

    @PatchMapping("/edit-expenseClaimEntry/{id}")
    public void updateExpenseClaimEntry(@PathVariable int id, @RequestBody Map<String,Object> expenseClaimEntryDTOmap) {
        expenseClaimEntryService.updateExpenseClaimEntry(expenseClaimEntryDTOmap,id);
    }

    @DeleteMapping("/delete-expenseClaimEntry/{id}")
    public void deleteExpenseClaimEntry(@PathVariable int id) {
        expenseClaimEntryService.deleteExpenseClaimEntry(id);
    }

    @PostMapping ("/type/employee")
    public List<ExpenseClaimEntryDTO> getExpenseClaimEntryByEmployeeAndType(@RequestBody Map<String,Object> expenseClaimEntryDTOmap) {
        Integer employeeId = (Integer) expenseClaimEntryDTOmap.get("employeeId");
        Integer typeId = (Integer) expenseClaimEntryDTOmap.get("typeId");
        return expenseClaimEntryService.findByEmployeeAndByType(employeeId,typeId);
    }

    @GetMapping("/sum-by-type")
    public List<TypeSumDTO> getSumByType(){
        return expenseClaimEntryService.findTypeSumByAllTypes();
    }

//    @GetMapping("/employee/{employeeId}")
//    public List<ExpenseClaimEntryDTO> getExpenseClaimEntryByEmployeeId(@PathVariable int employeeId) {
//        return expenseClaimEntryService.findByEmployeeId(employeeId);
//    }

}
