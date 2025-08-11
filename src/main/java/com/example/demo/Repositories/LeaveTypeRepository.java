package com.example.demo.Repositories;

import com.example.demo.Models.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

}
