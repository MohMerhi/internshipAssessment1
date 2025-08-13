package com.example.demo.Repositories;

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
    @Query("update ExpenseClaim ec set ec.total = :total where id = :id")
    int updateTotalExpenseClaim(@Param("id") int id, @Param("total") BigDecimal total);


}
