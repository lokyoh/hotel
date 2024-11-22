package com.lokyoh.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Bills {
    private Integer billId;
    private Integer checkinId;
    private LocalDate paymentTime;
    private BigDecimal paymentAmount;
}
