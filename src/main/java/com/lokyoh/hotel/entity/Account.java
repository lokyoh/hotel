package com.lokyoh.hotel.entity;

import lombok.Data;

@Data
public class Account {
    private Integer employeeId;
    private String account;
    private String password;
    private Integer level;
}
