package com.example.demo.Services;


import com.example.demo.DTOs.LeaveTypeDTO;
import com.example.demo.DTOs.LeaveTypeMapper;
import com.example.demo.Models.LeaveType;
import com.example.demo.Repositories.LeaveTypeRepository;
import com.example.demo.exceptions.InvalidRequestDataException;
import com.example.demo.exceptions.RequestValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeaveTypeServiceImpl implements LeaveTypeService{
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveTypeMapper leaveTypeMapper;
    private final BaseService baseService;
    private final RequestValidations requestValidations;

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
        List<String> errors = new ArrayList<>();
        requestValidations.validateName(leaveTypeDTOmap,errors,"name");
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        LeaveType leaveType = new LeaveType();
        baseService.updateEntity(leaveTypeDTOmap,leaveType,LeaveType.class);
        leaveType.setId(null);
        leaveTypeRepository.save(leaveType);
        return leaveType.getId();
    }
    public void updateLeaveType(Map<String,Object> leaveTypeDTOmap, int id) {
        LeaveType leaveType = leaveTypeRepository.findById(id).orElseThrow(()->new RuntimeException("LeaveType not found"));
        List<String> errors = new ArrayList<>();
        requestValidations.validateName(leaveTypeDTOmap,errors,"name");
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        baseService.updateEntity(leaveTypeDTOmap,leaveType,LeaveType.class);
        leaveTypeRepository.save(leaveType);

    }
    public void deleteLeaveType(int id) {
        leaveTypeRepository.deleteById(id);
    }



}
