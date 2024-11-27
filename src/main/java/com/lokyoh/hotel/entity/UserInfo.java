package com.lokyoh.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserInfo {
    private int id;
    private String name;
    private String gender;
    private String identification;
    private String phone;
    private String address;
    private BigDecimal totalSpent;
    private String membership;

    public UserInfo(Customers customer) {
        this.id = customer.getCustomerId();
        this.name = customer.getCname();
        this.gender = customer.getGender();
        this.identification = customer.getIdentification();
        this.phone = customer.getPhone();
        this.address = customer.getCaddress();
        this.totalSpent = customer.getTotalSpent();
        this.membership = customer.getMembership();
    }
}
