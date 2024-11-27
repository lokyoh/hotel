package com.lokyoh.hotel.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RCInfo {
    private int id;
    private String customerId;
    private String roomId;
    private String roomType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public RCInfo(Reservations reservation, String customerId) {
        this.id = reservation.getReservationId();
        this.customerId = customerId;
        this.roomId = reservation.getRoomId();
        this.roomType = reservation.getRtype();
        this.startDate = reservation.getExpectedCheckin();
        this.endDate = reservation.getExpectedCheckout();
        this.status = reservation.getRstatus();
    }

    public RCInfo(Checkins reservation, String customerId, String roomType) {
        this.id = reservation.getCheckinId();
        this.customerId = customerId;
        this.roomId = reservation.getRoomId();
        this.roomType = roomType;
        this.startDate = reservation.getCheckinTime();
        this.endDate = reservation.getDepartureTime();
        this.status = reservation.getRstatus();
    }
}
