package com.lokyoh.hotel.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Checkins {
    private Integer checkinId;
    private Integer customerId;
    private String roomId;
    private LocalDate checkinTime;
    private LocalDate departureTime;
    private Integer isChange;
    private String preid;
    private String rstatus;
}
