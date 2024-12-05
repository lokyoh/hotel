package com.lokyoh.hotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Employees {
    private Integer employeeId;
    @JsonIgnore
    private String epassword;
    @NotNull
    private String ename;
    @NotNull
    private String identification;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private LocalDate hireDate;
    @NotNull
    private String departmentName;
    @NotNull
    private String contactAddress;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
}
