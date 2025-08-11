package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "leaving")
public class Leaving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "leave_type_id", nullable = false)
    private Integer leave_type_id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @ColumnDefault("`start_date` + interval `number_of_days` - 1 day")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;

    @Lob
    @Column(name = "note")
    private String note;

    @NotNull
    @Column(name = "employee_id")
    private Integer employeeId;

}