package com.example.demo.Services;

import com.example.demo.DTOs.LeavingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeavingService {
    List<LeavingDTO> getAllLeavings();
    LeavingDTO getLeavingById(int id);
    int createLeaving(Map<String,Object> leavingDTOmap);
    void updateLeaving(Map<String,Object> leavingDTO, int id);
    void deleteLeaving(int id);
    Page<LeavingDTO> getLeavingByEmployeeId(int id, int page, int size);
    List<LeavingDTO> getLeavingByEmployeeAndDatesBetween(int employeeId, LocalDate startDate, LocalDate endDate);
    Page<LeavingDTO> getLeavingByLeaveType(int leaveType, int page, int size);
}
