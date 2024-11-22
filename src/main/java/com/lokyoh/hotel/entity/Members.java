package com.lokyoh.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Members {
    private String membership;
    private BigDecimal eligibleSpent;
    private BigDecimal discount;
}
