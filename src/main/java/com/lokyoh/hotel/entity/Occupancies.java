package com.lokyoh.hotel.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Occupancies {
    private Integer occupancyId;
    private Integer customerId;
    private String roomId;
    private LocalDate startTime;
    private LocalDate endTime;
}
