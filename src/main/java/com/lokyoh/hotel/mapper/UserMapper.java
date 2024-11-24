package com.lokyoh.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /// 根据用户ID获取用户名
    @Select("select cname from customers where customer_id=#{customerId}")
    String getCustomerNameById(Integer customerId);
}
