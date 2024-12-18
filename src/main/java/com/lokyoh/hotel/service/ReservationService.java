package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.PageBean;
import com.lokyoh.hotel.entity.RCInfo;
import com.lokyoh.hotel.entity.Reservations;
import jakarta.validation.constraints.NotNull;

public interface ReservationService {
    /// 添加预定
    void add(Reservations reservations);

    /// 修改预定单
    void modify(Reservations oldReservations, Reservations reservations);

    /// 获取指定预定单
    Reservations get(Integer reservationId, @NotNull Integer customerId);

    /// 取消指定预定单
    void cancel(Reservations reservation);

    /// 获取占用单号
    Integer getOccupancyId(Integer reservationId, Integer customerId);

    /// 完成预定
    void checkout(Integer id);

    /// 获取预定列表
    PageBean<RCInfo> list(Integer pageNum, Integer pageSize, String phone, String roomType, String status);
}
