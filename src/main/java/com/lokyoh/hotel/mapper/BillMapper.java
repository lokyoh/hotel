package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Bills;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper {
    /// 添加交易单
    @Insert("insert into bills (checkin_id, payment_time, payment_amount) values (#{checkinId}, #{paymentTime}, #{paymentAmount})")
    void add(Bills bills);
}
