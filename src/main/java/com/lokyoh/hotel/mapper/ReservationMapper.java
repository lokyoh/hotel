package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Reservations;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReservationMapper {
    /// 添加新的预定
    @Insert("insert into reservations (customer_id, rtype, room_id, expected_checkin, expected_checkout)" +
            " values (#{customerId}, #{rtype}, #{roomId}, #{expectedCheckin}, #{expectedCheckout})")
    void add(Reservations reservations);

    /// 获取指定信息的预定单
    @Select("select * from reservations where reservation_id=#{reservationId} and customer_id=#{customerId}")
    Reservations get(Integer reservationId, Integer customerId);

    /// 修改指定账单
    @Update("update reservations set rtype=#{rtype}, room_id=#{roomId}, expected_checkin=#{expectedCheckin}, expected_checkout=#{expectedCheckout} where reservation_id=#{reservationId}")
    void modify(Reservations reservations);

    /// 取消指定账单
    @Update("update reservations set rstatus='已取消' where reservation_id=#{reservationId}")
    void cancel(Integer reservationId);

    ///  完成预定
    @Update("update reservations set rstatus='已完成' where reservation_id=#{reservationId}")
    void checkout(Integer reservationId);

    /// 获取预定列表
    List<Reservations> list(Integer customerId, String roomType, String status);
}
