package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Checkins;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckinMapper {
    /// 新建入住
    @Insert("insert into checkins (customer_id, room_id, checkin_time, departure_time, preid)" +
            " values (#{customerId}, #{roomId}, #{checkinTime}, #{departureTime}, #{preid})")
    void add(Checkins checkins);
}
