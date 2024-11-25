package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Checkins;
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
    @Select("select occupancy_id from occupancies where room_id=#{roomId}")
    List<Integer> getRoomOccupied(String roomId);

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

    /// 新建入住
    @Insert("insert into checkins (customer_id, room_id, checkin_time, departure_time, preid)" +
            " values (#{customerId}, #{roomId}, #{checkinTime}, #{departureTime}, #{preid})")
    void newCheckin(Checkins checkins);

    /// 获取最近占用房间信息
    @Select("select * from occupancies where start_time=(select MIN(start_time) from occupancies where room_id=#{roomId}) and room_id=#{roomId}")
    Occupancies getOccupancies(String roomId);

    @Select("select customer_id from cohabit where occupancy_id=#{occupancyId}")
    List<Integer> getCohabitUserId(Integer occupancyId);
}
