package com.lokyoh.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomInfo {
    private String id;
    private String type;
    private Integer status;
    private String customer;
}
