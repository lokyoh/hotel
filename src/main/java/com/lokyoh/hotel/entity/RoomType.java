package com.lokyoh.hotel.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomType {
    private String rtype;
    private BigDecimal area;
    private Integer number;
    private String specification;
    private BigDecimal price;
    private Integer rnumber;
}
