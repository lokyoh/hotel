package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Checkins;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CheckinMapper {
    /// 新建入住
    @Insert("insert into checkins (customer_id, room_id, checkin_time, departure_time, preid)" +
            " values (#{customerId}, #{roomId}, #{checkinTime}, #{departureTime}, #{preid})")
    void add(Checkins checkins);

    /// 获取指定入住单
    @Select("select * from checkins where checkin_id=#{checkinId} and customer_id=#{customerId}")
    Checkins get(Integer checkinId, Integer customerId);

    /// 取消指定入住
    @Update("update checkins set rstatus='已取消' where checkin_id=#{checkinId}")
    void cancel(Integer checkinId);

    /// 完成入住
    @Update("update checkins set rstatus='已完成' where checkin_id=#{checkinId}")
    void checkout(Integer checkinId);

    /// 获取入住列表
    List<Checkins> list(Integer customerId, String roomType, String status);
}
