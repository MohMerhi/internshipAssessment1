package com.example.demo.Controllers;

import com.example.demo.DTOs.LeavingDTO;
import com.example.demo.Repositories.LeavingRepository;
import com.example.demo.Services.LeavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leavings")
public class LeavingController {
    private final LeavingService leavingService;

    @GetMapping("/{id}")
    public LeavingDTO getLeaving(@PathVariable int id) {
        return leavingService.getLeavingById(id);
    }


    @GetMapping
    public List<LeavingDTO> getAllLeavings() {
        return leavingService.getAllLeavings();
    }

    @PostMapping("/create-leaving")
    public int addLeaving(@RequestBody Map<String,Object> leavingDTOmap) {
        return leavingService.createLeaving(leavingDTOmap);

    }

    @PatchMapping("/edit-leaving/{id}")
    public void updateLeaving(@PathVariable int id, @RequestBody Map<String,Object> leavingDTOmap) {
        leavingService.updateLeaving(leavingDTOmap,id);
    }

    @DeleteMapping("/delete-leaving/{id}")
    public void deleteLeaving(@PathVariable int id) {
        leavingService.deleteLeaving(id);
    }

    @PostMapping("/employee/{id}/in-range")
    public List<LeavingDTO> inRangeLeaving(@PathVariable int id, @RequestBody Map<String,Object> leavingDTOmap) throws ParseException {
        Object from = leavingDTOmap.get("from");
        Object to = leavingDTOmap.get("to");
        if(from != null && to != null) {
            LocalDate fromDate = LocalDate.parse(from.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate toDate = LocalDate.parse(to.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
            return leavingService.getLeavingByEmployeeAndDatesBetween(id, fromDate, toDate);
        }
        else{
            throw new RuntimeException("missing one or both dates");
        }
    }

    @PostMapping("/leaveType/{typeId}")
    public Page<LeavingDTO> leavingByLeaveType(@PathVariable int typeId, @RequestBody Map<String,Object> pageMap) throws ParseException {
        Integer pageNumber = Integer.parseInt(pageMap.getOrDefault("pageNumber",0).toString());
        Integer pageSize = Integer.parseInt(pageMap.getOrDefault("pageSize",10).toString());
        return leavingService.getLeavingByLeaveType(typeId,pageNumber,pageSize);
    }

    @PostMapping("/employee/{employeeId}")
    public Page<LeavingDTO> leavingByEmployee(@PathVariable int employeeId, @RequestBody Map<String,Object> pageMap) throws ParseException {
        Integer pageNumber = Integer.parseInt(pageMap.getOrDefault("pageNumber",0).toString());
        Integer pageSize = Integer.parseInt(pageMap.getOrDefault("pageSize",10).toString());
        return leavingService.getLeavingByEmployeeId(employeeId,pageNumber,pageSize);
    }


}
