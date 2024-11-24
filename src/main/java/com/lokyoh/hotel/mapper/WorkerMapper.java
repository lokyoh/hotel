package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Room;
import com.lokyoh.hotel.entity.RoomType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkerMapper {
    // 查询指定员工账户
    @Select("select * from account where account=#{account}")
    Account findByAccount(String account);

    // 获取房间列表
    @Select("select * from room")
    List<Room> getRooms();

    @Select("select * from occupancies where end_time>=now()")
    List<Occupancies> getNowOccupy();

    @Select("select cname from customers where customer_id=#{customerId}")
    String getCustomerNameById(Integer customerId);
}
