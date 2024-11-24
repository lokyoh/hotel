package com.lokyoh.hotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Account {
    private Integer employeeId;
    private String account;
    @JsonIgnore
    private String password;
    private Integer level;
}
