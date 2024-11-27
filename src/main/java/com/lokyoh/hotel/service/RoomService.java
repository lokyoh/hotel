package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.*;

import java.time.LocalDate;

public interface RoomService {
    /// 获取房间列表
    PageBean<RoomInfo> getRooms();

    /// 检查房间是否被占用,未占用为真
    boolean checkRoom(String roomId, LocalDate startTime, LocalDate endTime);

    /// 获取指定房间最近一次占用信息
    RoomDetail getOccupyInfo(String roomId);

    /// 修改占用表
    void modifyOccupancy(Occupancies oldOccupancies, Occupancies occupancies);

    /// 添加同居客户
    void addCohabit(Integer occupancyId, Integer customerId);

    /// 删除同居用户
    void delCohabit(Integer occupancyId, Integer customerId);

    /// 获取房间类型
    String getRoomType(String roomId);

    /// 删除占用
    void delOccupancy(Integer oId);
}
