package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.*;

public interface RoomService {
    /// 获取房间列表
    Rooms getRooms();

    /// 检查房间是否被占用
    boolean checkRoom(String roomId);

    /// 添加预定
    void newReservation(Reservations reservations);

    /// 添加入住
    void newCheckin(Checkins checkins);

    /// 获取指定房间最近一次占用信息
    RoomDetail getOccupyInfo(String roomId);
}
