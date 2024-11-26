package com.lokyoh.hotel.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservations {
    private Integer reservationId;
    @NotNull
    private Integer customerId;
    private String rtype;
    @NotNull
    private String roomId;
    @NotNull
    private LocalDate expectedCheckin;
    @NotNull
    private LocalDate expectedCheckout;
    private String rstatus;
}
