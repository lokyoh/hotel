package com.lokyoh.hotel.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservations {
    private Integer reservationId;
    private Integer customerId;
    private String rtype;
    private String roomId;
    private Integer numberOfGuests;
    private LocalDate expectedCheckin;
    private LocalDate expectedCheckout;
    private String rstatus;
}
