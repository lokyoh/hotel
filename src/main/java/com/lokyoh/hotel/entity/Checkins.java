package com.lokyoh.hotel.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Checkins {
    private Integer checkinId;
    @NotNull
    private Integer customerId;
    @NotNull
    private String roomId;
    @NotNull
    private LocalDate checkinTime;
    @NotNull
    private LocalDate departureTime;
    private Integer isChange;
    private String preid;
    private String rstatus;
}
