package com.lokyoh.hotel.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RoomDetail {
    private String roomId;
    private String type;
    private LocalDate startTime;
    private LocalDate endTime;
    private Integer customerId;
    private List<String> resident;
}
