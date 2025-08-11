package com.example.demo.Repositories;

import com.example.demo.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
