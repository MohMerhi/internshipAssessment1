package com.example.demo.DTOs;

import com.example.demo.Models.LeaveType;
import lombok.Data;
import org.hibernate.cache.spi.SecondLevelCacheLogger;

import java.time.LocalDate;

@Data
public class LeavingDTO {
    private int id;
    private Integer leave_type_id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfDays;
    private String note;
    private Integer employee_id;

}
