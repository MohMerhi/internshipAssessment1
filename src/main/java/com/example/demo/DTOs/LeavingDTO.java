package com.example.demo.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeavingDTO {
    private int id;
    private Integer leaveTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfDays;
    private String note;
    private Integer employeeId;

}
