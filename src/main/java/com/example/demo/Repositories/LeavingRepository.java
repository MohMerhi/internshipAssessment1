package com.example.demo.Repositories;

import com.example.demo.Models.Leaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface LeavingRepository extends JpaRepository<Leaving, Integer> {
    List<Leaving> findByEmployeeIdAndStartDateBetween(Integer employeeId, Date startDate, Date endDate);
    Page<Leaving> findByEmployeeId(Integer employeeId, Pageable pageable);
    @Query("SELECT l FROM Leaving l, LeaveType lt WHERE lt.id = l.leave_type_id AND lt.name = :leaveTypeName")
    Page<Leaving> findByLeaveType(@Param("leaveTypeName") String leaveTypeName, Pageable pageable);
}
