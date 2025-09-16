package com.example.demo.Controllers;

import com.example.demo.DTOs.DepartmentDTO;
import com.example.demo.Repositories.DepartmentRepository;
import com.example.demo.Services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public DepartmentDTO getDepartment(@PathVariable int id) {
        return departmentService.getDepartmentById(id);
    }


    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/create-department")
    public int addDepartment(@RequestBody Map<String,Object> departmentDTOmap) {
        return departmentService.createDepartment(departmentDTOmap);

    }

    @PatchMapping("/edit-department/{id}")
    public void updateDepartment(@PathVariable int id, @RequestBody Map<String,Object> departmentDTOmap) {
        departmentService.updateDepartment(departmentDTOmap,id);
    }

    @DeleteMapping("/delete-department/{id}")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
    }



}
