package com.example.demo.Services;


import com.example.demo.DTOs.LeavingDTO;
import com.example.demo.DTOs.LeavingMapper;
import com.example.demo.Models.Leaving;
import com.example.demo.Repositories.LeavingRepository;
import com.example.demo.exceptions.InvalidRequestDataException;
import com.example.demo.exceptions.RequestValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeavingServiceImpl implements LeavingService{
    private final LeavingRepository leavingRepository;
    private final LeavingMapper leavingMapper;
    private final BaseService baseService;
    private final RequestValidations requestValidations;
    private final View error;

    public List<LeavingDTO> getAllLeavings() {
        return leavingRepository.findAll()
                .stream()
                .map(leavingMapper::toLeavingDTO)
                .collect(Collectors.toList());
    }

    public LeavingDTO getLeavingById(int id) {
        Leaving leaving = leavingRepository.findById(id).orElse(null);
        return leavingMapper.toLeavingDTO(leaving);
    }
    public int createLeaving(Map<String,Object> leavingDTOmap) {
        List<String> errors = new ArrayList<>();
        requestValidations.validatePositiveInteger(leavingDTOmap, "leaveTypeId",errors,false);
        requestValidations.validateDate(leavingDTOmap, "startDate",errors);
        requestValidations.validatePositiveInteger(leavingDTOmap, "numberOfDays",errors,false);
        requestValidations.validatePositiveInteger(leavingDTOmap, "employeeId",errors,false);
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        Leaving leaving = new Leaving();
        baseService.updateEntity(leavingDTOmap,leaving,Leaving.class);
        leaving.setId(null);

        leavingRepository.save(leaving);
        return leaving.getId();
    }
    public void updateLeaving(Map<String,Object> leavingDTOmap, int id) {
        Leaving leaving = leavingRepository.findById(id).orElseThrow(()->new RuntimeException("Leaving not found"));
        List<String> errors = new ArrayList<>();
        requestValidations.validatePositiveInteger(leavingDTOmap, "leaveTypeId",errors,false);
        requestValidations.validateDate(leavingDTOmap, "startDate",errors);
        requestValidations.validatePositiveInteger(leavingDTOmap, "numberOfDays",errors,false);
        requestValidations.validatePositiveInteger(leavingDTOmap, "employeeId",errors,false);
        if(!errors.isEmpty()) {
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        baseService.updateEntity(leavingDTOmap,leaving,Leaving.class);


        leaving.setId(id);
        leavingRepository.save(leaving);

    }
    public void deleteLeaving(int id) {
        leavingRepository.deleteById(id);
    }

    @Override
    public Page<LeavingDTO> getLeavingByEmployeeId(int employeeId, Map<String,Object> pageMap) {
        List<String> errors = new ArrayList<>();
        requestValidations.validatePositiveInteger(pageMap, "pageNumber", errors, true);
        requestValidations.validatePositiveInteger(pageMap, "pageSize", errors, false);
        if(!errors.isEmpty()){
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        Integer page = Integer.parseInt(pageMap.getOrDefault("pageNumber",0).toString());
        Integer size = Integer.parseInt(pageMap.getOrDefault("pageSize",10).toString());
        Pageable pageable =  PageRequest.of(page, size);
        return leavingRepository.findByEmployeeId(employeeId, pageable)
                .map(leavingMapper::toLeavingDTO);
    }

    @Override
    public List<LeavingDTO> getLeavingByEmployeeAndDatesBetween(int employeeId, Map<String,Object> leavingDTOmap) {
        List<String> errors = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Object from = leavingDTOmap.get("from");
        Object to = leavingDTOmap.get("to");
        List<Leaving> leavingList;
        if(from == null && to == null) {
            leavingList = leavingRepository.findByEmployeeId(employeeId);
        }
        else if(from == null){
            requestValidations.validateDate(leavingDTOmap,"to", errors);
            if(!errors.isEmpty()) {
                throw new InvalidRequestDataException("Validation Error", errors);
            }
            leavingList = leavingRepository.findByEmployeeIdAndStartDateBefore(employeeId,LocalDate.parse(to.toString(),formatter));
        }
        else if (to == null){
            requestValidations.validateDate(leavingDTOmap,"from", errors);
            if(!errors.isEmpty()) {
                throw new InvalidRequestDataException("Validation Error", errors);
            }
            leavingList = leavingRepository.findByEmployeeIdAndStartDateAfter(employeeId,  LocalDate.parse(from.toString(),formatter));
        }
        else{
            requestValidations.validateDate(leavingDTOmap,"from", errors);
            requestValidations.validateDate(leavingDTOmap,"to", errors);
            if(!errors.isEmpty()) {
                throw new InvalidRequestDataException("Validation Error", errors);
            }
            leavingList = leavingRepository.findByEmployeeIdAndStartDateBetween(employeeId,LocalDate.parse(from.toString(),formatter),LocalDate.parse(to.toString(),formatter));
        }
        return  leavingList
                .stream()
                .map(leavingMapper::toLeavingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LeavingDTO> getLeavingByLeaveType(int leaveType, Map<String,Object> pageMap) {
        List<String> errors = new ArrayList<>();
        requestValidations.validatePositiveInteger(pageMap, "pageNumber", errors, true);
        requestValidations.validatePositiveInteger(pageMap, "pageSize", errors, false);
        if(!errors.isEmpty()){
            throw new InvalidRequestDataException("Validation Error", errors);
        }
        Integer page = Integer.parseInt(pageMap.getOrDefault("pageNumber",0).toString());
        Integer size = Integer.parseInt(pageMap.getOrDefault("pageSize",10).toString());
        Pageable pageable =  PageRequest.of(page, size);
        return leavingRepository.findByLeaveTypeId(leaveType, pageable)
                .map(leavingMapper::toLeavingDTO);
    }
}
