package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.Checkins;
import com.lokyoh.hotel.entity.PageBean;
import com.lokyoh.hotel.entity.RCInfo;

public interface CheckinService {
    /// 添加入住
    void add(Checkins checkins);

    /// 获取指定入住单
    Checkins get(Integer checkinId, Integer customerId);

    /// 获取占用id
    Integer getOccupancyId(Integer checkinId, Integer customerId);

    /// 取消指定入住
    void cancel(Checkins checkin);

    /// 完成入住
    void checkout(Integer checkinId);

    /// 获取入住列表
    PageBean<RCInfo> list(Integer pageNum, Integer pageSize, String phone, String roomType, String status);
}
