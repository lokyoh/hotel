package com.lokyoh.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Customers {
    private Integer customerId;
    private String identification;
    private String cname;
    private String gender;
    private String caddress;
    private String phone;
    private String membership;
    private BigDecimal totalSpent;
}
