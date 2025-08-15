package com.example.demo.Repositories;

import com.example.demo.DTOs.TypeSumDTO;
import com.example.demo.Models.ExpenseClaimEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseClaimEntryRepository extends JpaRepository<ExpenseClaimEntry, Integer> {
    @Query("Select Sum(ece.total) From ExpenseClaimEntry ece where ece.expenseClaimId = :expenseclaimid")
    BigDecimal getTotalExpenseClaim(@Param("expenseclaimid") int expenseclaimid);

    @Modifying
    @Query("update ExpenseClaim ec set ec.total = :total where ec.id = :id")
    int updateTotalExpenseClaim(@Param("id") int id, @Param("total") BigDecimal total);

    @Query(" Select ece from ExpenseClaimEntry ece Join ExpenseClaim ec ON ece.expenseClaimId = ec.id where ec.employeeId = :employeeId and ece.expenseTypeId = :expenseTypeId")
    List<ExpenseClaimEntry> findAllClaimEntriesByEmployeeIdAndType(Integer employeeId, Integer expenseTypeId);

    @Query("SELECT new  com.example.demo.DTOs.TypeSumDTO(et.name,SUM(e.total)) FROM ExpenseClaimEntry e JOIN ExpenseType et on et.id = e.expenseTypeId GROUP BY e.expenseTypeId")
    List<TypeSumDTO> getTotalGroupedByType();

//    List<ExpenseClaimEntry> findAllByEmployeeId(Integer employeeId);
}
