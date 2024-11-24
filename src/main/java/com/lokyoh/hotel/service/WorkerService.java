package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Room;
import com.lokyoh.hotel.entity.RoomType;

import java.util.List;

public interface WorkerService {
    // 根据员工账号寻找员工账户数据
    Account findByAccount(String account);

    // 获取房间列表
    List<Room> getRooms();

    // 获取目前占用表
    List<Occupancies> getNowOccupy();

    String getCustomerNameById(Integer customerId);
}
