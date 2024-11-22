package com.lokyoh.hotel.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employees {
    private Integer employeeId;
    private String epassword;
    private String ename;
    private String identification;
    private String gender;
    private LocalDate birthday;
    private LocalDate hireDate;
    private String departmentName;
    private String contactAddress;
    private String phoneNumber;
    private String email;
}
