package com.lokyoh.hotel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Occupancies {
    private Integer occupancyId;
    private Integer customerId;
    private String roomId;
    private LocalDate startTime;
    private LocalDate endTime;

    public Occupancies(Reservations reservations){
        this.occupancyId = null;
        this.customerId = reservations.getCustomerId();
        this.roomId = reservations.getRoomId();
        this.startTime = reservations.getExpectedCheckin();
        this.endTime = reservations.getExpectedCheckout();
    }

    public Occupancies(Checkins checkins){
        this.occupancyId = null;
        this.customerId = checkins.getCustomerId();
        this.roomId = checkins.getRoomId();
        this.startTime = checkins.getCheckinTime();
        this.endTime = checkins.getDepartureTime();
    }
}
