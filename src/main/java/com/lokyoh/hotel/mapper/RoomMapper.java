package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Room;
import com.lokyoh.hotel.entity.RoomType;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RoomMapper {
    /// 获取房间列表
    @Select("select * from room")
    List<Room> getRooms();

    /// 获取占用
    @Select("select * from occupancies")
    List<Occupancies> getAllOccupy();

    ///  获取房间占用列表
    @Select("select occupancy_id from occupancies where room_id=#{roomId} and not(start_time>#{endTime} or end_time<#{startTime})")
    List<Integer> getRoomHasOccupied(String roomId, LocalDate startTime, LocalDate endTime);

    /// 获取房间类型``
    @Select("select rtype from room where room_id=#{roomId}")
    String getRoomType(String roomId);

    /// 新建占用
    @Insert("insert into occupancies (customer_id, room_id, start_time, end_time)" +
            " values (#{customerId}, #{roomId}, #{startTime}, #{endTime})")
    void addOccupancy(Occupancies occupancies);

    /// 获取最近占用房间信息
    @Select("select * from occupancies where start_time=(select MIN(start_time) from occupancies where room_id=#{roomId}) and room_id=#{roomId}")
    Occupancies getResentOccupancies(String roomId);

    /// 获取同居用户id
    @Select("select customer_id from cohabit where occupancy_id=#{occupancyId}")
    List<Integer> getCohabitUserId(Integer occupancyId);

    /// 获取房间占用信息
    @Select("select * from occupancies where room_id=#{roomId}")
    List<Occupancies> getOccupyByRoomId(String roomId);

    /// 修改入住信息
    @Update("update occupancies set room_id=#{occupancies.roomId}, start_time=#{occupancies.startTime}, end_time=#{occupancies.endTime}" +
            " where room_id=#{oldOccupancies.roomId} and start_time=#{oldOccupancies.startTime}")
    void modifyOccupancy(Occupancies oldOccupancies, Occupancies occupancies);

    ///  删除占用
    @Delete("delete from occupancies where occupancy_id=#{occupancyId}")
    void delOccupancy(Integer occupancyId);

    /// 获取占用表id
    @Select("select occupancy_id from occupancies where room_id=#{roomId} and start_time=#{startTime}")
    Integer getOccupancyId(String roomId, LocalDate startTime);

    /// 添加同居
    @Insert("insert into cohabit (occupancy_id, customer_id)" +
            " values (#{occupancyId}, #{customerId})")
    void addCohabit(Integer occupancyId, Integer customerId);

    /// 删除同居
    @Delete("delete from cohabit where occupancy_id=#{occupancyId} and customer_id=#{customerId}")
    void delCohabit(Integer occupancyId, Integer customerId);

    List<Room> roomList(String type);

    @Insert("insert into room (room_id, rtype) values (#{roomId}, #{rtype})")
    void add(Room room);

    @Update("update room set rtype=#{rtype} where room_id=#{roomId}")
    void modify(Room room);

    @Delete("delete from room where room_id=#{id}")
    void del(Integer id);

    @Insert("insert into roomtype (rtype, area, number, specification, price, rnumber)" +
            " values (#{rtype}, #{area}, #{number}, #{specification}, #{price}, #{rnumber})")
    void addType(RoomType roomType);

    @Delete("delete from roomtype where rtype=#{type}")
    void delType(String type);
}
