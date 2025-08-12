package com.example.demo.Services;

import com.example.demo.DTOs.LeaveTypeDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeaveTypeService {
    List<LeaveTypeDTO> getAllLeaveTypes();
    LeaveTypeDTO getLeaveTypeById(int id);
    int createLeaveType(Map<String,Object> leaveTypeDTOmap);
    void updateLeaveType(Map<String,Object> leaveTypeDTO, int id);
    void deleteLeaveType(int id);

}
