package com.lokyoh.hotel.entity;

import lombok.Data;

@Data
public class UserAccount {
    private Integer customerId;
    private String account;
    private String password;
}
