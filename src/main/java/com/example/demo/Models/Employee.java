package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @NotBlank(message = "name must not be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Email
    @NotBlank(message = "email must not be empty")
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @NotBlank(message = "address must not be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @NotBlank(message = "department id must be provided")
    @Column(name="department_id", nullable = false)
    private Integer department_id;

}