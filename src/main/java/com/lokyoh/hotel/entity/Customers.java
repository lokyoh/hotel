package com.lokyoh.hotel.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Customers {
    private Integer customerId;
    @NotNull
    private String identification;
    @NotNull
    private String cname;
    @NotNull
    private String gender;
    @NotNull
    private String caddress;
    @NotNull
    private String phone;
    private String membership;
    private BigDecimal totalSpent;
}
