package com.example.demo.Repositories;
import com.example.demo.Models.Leaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface LeavingRepository extends JpaRepository<Leaving, Integer> {
    List<Leaving> findByEmployeeIdAndStartDateBetween(Integer employeeId, LocalDate startDate, LocalDate endDate);
    List<Leaving> findByEmployeeIdAndStartDateAfter(Integer employeeId, LocalDate startDate);
    List<Leaving> findByEmployeeIdAndStartDateBefore(Integer employeeId, LocalDate endDate);
    List<Leaving> findByEmployeeId(Integer employeeId);
    Page<Leaving> findByEmployeeId(Integer employeeId, Pageable pageable);
    Page<Leaving> findByLeaveTypeId(Integer leaveTypeId, Pageable pageable);

}
