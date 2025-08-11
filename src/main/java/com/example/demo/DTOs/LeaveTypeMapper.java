package com.example.demo.DTOs;

import com.example.demo.Models.LeaveType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    LeaveTypeDTO toLeaveTypeDTO(LeaveType leaveType);
}
