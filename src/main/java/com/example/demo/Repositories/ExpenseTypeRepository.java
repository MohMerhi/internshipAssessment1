package com.example.demo.Repositories;

import com.example.demo.Models.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Integer> {

}
