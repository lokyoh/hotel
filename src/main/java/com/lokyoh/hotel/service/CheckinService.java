package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.Checkins;

public interface CheckinService {
    /// 添加入住
    void add(Checkins checkins);

    /// 获取指定入住单
    Checkins get(Integer checkinId, Integer customerId);

    /// 获取占用id
    Integer getOccupancyId(Integer checkinId, Integer customerId);
}
