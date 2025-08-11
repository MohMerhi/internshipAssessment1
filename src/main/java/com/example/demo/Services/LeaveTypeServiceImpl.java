package com.example.demo.Services;


import com.example.demo.DTOs.LeaveTypeDTO;
import com.example.demo.DTOs.LeaveTypeMapper;
import com.example.demo.Models.LeaveType;
import com.example.demo.Repositories.LeaveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService{
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveTypeMapper leaveTypeMapper;
    private final BaseService baseService;

    public List<LeaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeRepository.findAll()
                .stream()
                .map(leaveTypeMapper::toLeaveTypeDTO)
                .collect(Collectors.toList());
    }

    public LeaveTypeDTO getLeaveTypeById(int id) {
        LeaveType leaveType = leaveTypeRepository.findById(id).orElse(null);
        return leaveTypeMapper.toLeaveTypeDTO(leaveType);
    }
    public int createLeaveType(Map<String,Object> leaveTypeDTOmap) {
        LeaveType leaveType = new LeaveType();
        baseService.updateEntity(leaveTypeDTOmap,leaveType,LeaveType.class);
        if(leaveType.getId() != null && leaveTypeRepository.findById(leaveType.getId()).isPresent()) {
            throw new DuplicateKeyException("LeaveType already exists");
        }
        leaveTypeRepository.save(leaveType);
        return leaveType.getId();
    }
    public void updateLeaveType(Map<String,Object> leaveTypeDTOmap, int id) {
        LeaveType leaveType = leaveTypeRepository.findById(id).orElseThrow(()->new RuntimeException("LeaveType not found"));
        baseService.updateEntity(leaveTypeDTOmap,leaveType,LeaveType.class);
        leaveType.setId(id);
        leaveTypeRepository.save(leaveType);

    }
    public void deleteLeaveType(int id) {
        leaveTypeRepository.deleteById(id);
    }
}
