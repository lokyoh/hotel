package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.entity.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomMapper {
    /// 获取房间列表
    @Select("select * from room")
    List<Room> getRooms();

    /// 获取占用
    @Select("select * from occupancies")
    List<Occupancies> getNowOccupy();

    ///  获取房间占用情况
    @Select("select * from occupancies where room_id=#{roomId}")
    List<Occupancies> getRoomOccupied(String roomId);

    /// 添加新的预定
    @Insert("insert into reservations (customer_id, rtype, room_id, number_of_guests, expected_checkin, expected_checkout)" +
            " values (#{customerId}, #{rtype}, #{roomId}, #{numberOfGuests}, #{expectedCheckin}, #{expectedCheckout})")
    void newReservation(Reservations reservations);

    /// 获取房间类型
    @Select("select rtype from room where room_id=#{roomId}")
    String getRoomType(String roomId);

    /// 新建占用
    @Insert("insert into occupancies (customer_id, room_id, start_time, end_time)" +
            " values (#{customerId}, #{roomId}, #{startTime}, #{endTime})")
    void newOccupancy(Occupancies occupancies);
}
