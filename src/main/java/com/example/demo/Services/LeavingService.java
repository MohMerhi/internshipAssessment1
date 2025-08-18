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
    Page<LeavingDTO> getLeavingByEmployeeId(int id, Map<String,Object> pageMap);
    List<LeavingDTO> getLeavingByEmployeeAndDatesBetween(int employeeId, Map<String,Object> leavingDTOmap);
    Page<LeavingDTO> getLeavingByLeaveType(int leaveType, Map<String,Object> pageMap);
}
