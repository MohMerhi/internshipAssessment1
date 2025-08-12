package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "expense_claim_entry")
public class ExpenseClaimEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "expense_type_id", nullable = false)
    private Integer expenseTypeId;

    @NotNull
    @Column(name = "expense_claim_id", nullable = false)
    private Integer expenseClaimId;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "total", nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

}