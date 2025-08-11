package com.example.demo.Controllers;

import com.example.demo.DTOs.LeaveTypeDTO;
import com.example.demo.Repositories.LeaveTypeRepository;
import com.example.demo.Services.LeaveTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leaveTypes")
public class LeaveTypeController {
    private final LeaveTypeService leaveTypeService;

    @GetMapping("/{id}")
    public LeaveTypeDTO getLeaveType(@PathVariable int id) {
        return leaveTypeService.getLeaveTypeById(id);
    }


    @GetMapping
    public List<LeaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }

    @PostMapping("/create-leaveType")
    public int addLeaveType(@RequestBody Map<String,Object> leaveTypeDTOmap) {
        return leaveTypeService.createLeaveType(leaveTypeDTOmap);

    }

    @PatchMapping("/edit-leaveType/{id}")
    public void updateLeaveType(@PathVariable int id, @RequestBody Map<String,Object> leaveTypeDTOmap) {
        leaveTypeService.updateLeaveType(leaveTypeDTOmap,id);
    }

    @DeleteMapping("/delete-leaveType/{id}")
    public void deleteLeaveType(@PathVariable int id) {
        leaveTypeService.deleteLeaveType(id);
    }



}
