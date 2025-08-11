package com.example.demo.Repositories;

import com.example.demo.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e, Department d WHERE e.department_id = d.id AND d.name = :deptName")
    List<Employee> findByDepartmentName(@Param("deptName") String departmentName);
}
