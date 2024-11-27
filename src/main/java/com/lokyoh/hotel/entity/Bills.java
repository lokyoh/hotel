package com.lokyoh.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Bills {
    private Integer billId;
    private Integer checkinId;
    private LocalDateTime paymentTime;
    private BigDecimal paymentAmount;
}
