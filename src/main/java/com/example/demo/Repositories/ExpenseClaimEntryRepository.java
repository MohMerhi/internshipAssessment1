package com.example.demo.Repositories;

import com.example.demo.Models.ExpenseClaimEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseClaimEntryRepository extends JpaRepository<ExpenseClaimEntry, Integer> {

}
