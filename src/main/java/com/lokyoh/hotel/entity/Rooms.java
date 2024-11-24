package com.lokyoh.hotel.entity;

import lombok.Data;

import java.util.List;

@Data
public class Rooms {
    private Integer count;
    private List<RoomInfo> rooms;
}
