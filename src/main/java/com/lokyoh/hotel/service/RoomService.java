package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.entity.Room;

import java.util.List;

public interface RoomService {
    /// 获取房间列表
    List<Room> getRooms();

    /// 获取目前占用表
    List<Occupancies> getNowOccupy();

    /// 检查房间是否被占用
    boolean checkRoom(String roomId);

    /// 添加预定
    void newReservation(Reservations reservations);
}
