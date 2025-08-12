package com.example.demo.Services;


import com.example.demo.DTOs.LeavingDTO;
import com.example.demo.DTOs.LeavingMapper;
import com.example.demo.Models.Leaving;
import com.example.demo.Repositories.LeavingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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
        Leaving leaving = new Leaving();
        baseService.updateEntity(leavingDTOmap,leaving,Leaving.class);
        if(leaving.getId() != null && leavingRepository.findById(leaving.getId()).isPresent()) {
            throw new DuplicateKeyException("Leaving already exists");
        }
        leavingRepository.save(leaving);
        return leaving.getId();
    }
    public void updateLeaving(Map<String,Object> leavingDTOmap, int id) {
        Leaving leaving = leavingRepository.findById(id).orElseThrow(()->new RuntimeException("Leaving not found"));
        baseService.updateEntity(leavingDTOmap,leaving,Leaving.class);
        leaving.setId(id);
        leavingRepository.save(leaving);

    }
    public void deleteLeaving(int id) {
        leavingRepository.deleteById(id);
    }

    @Override
    public Page<LeavingDTO> getLeavingByEmployeeId(int id, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        return leavingRepository.findByEmployeeId(id, pageable)
                .map(leavingMapper::toLeavingDTO);
    }

    @Override
    public List<LeavingDTO> getLeavingByEmployeeAndDatesBetween(int employeeId, LocalDate startDate, LocalDate endDate) {
        return leavingRepository.findByEmployeeIdAndStartDateBetween(employeeId,startDate,endDate)
                .stream()
                .map(leavingMapper::toLeavingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LeavingDTO> getLeavingByLeaveType(String leaveType, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        return leavingRepository.findByLeaveType(leaveType, pageable)
                .map(leavingMapper::toLeavingDTO);

    }
}
