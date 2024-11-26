package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Checkins;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckinMapper {
    /// 新建入住
    @Insert("insert into checkins (customer_id, room_id, checkin_time, departure_time, preid)" +
            " values (#{customerId}, #{roomId}, #{checkinTime}, #{departureTime}, #{preid})")
    void add(Checkins checkins);

    /// 获取指定入住单
    @Select("select * from chechins where checkin_id=#{checkinId} and customer_id=#{customerId}")
    Checkins get(Integer checkinId, Integer customerId);
}
