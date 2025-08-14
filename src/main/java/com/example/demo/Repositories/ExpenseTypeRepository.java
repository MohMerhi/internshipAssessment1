package com.example.demo.Repositories;

import com.example.demo.Models.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Integer> {
    @Query("SELECT et.id FROM ExpenseType et WHERE et.name = :name")
    Integer findIdByName(@Param("name") String name);
}
