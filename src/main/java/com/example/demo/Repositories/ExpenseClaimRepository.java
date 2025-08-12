package com.example.demo.Repositories;

import com.example.demo.Models.ExpenseClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer> {

}
