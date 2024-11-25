package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Customers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /// 根据用户ID获取用户名
    @Select("select cname from customers where customer_id=#{customerId}")
    String getCustomerNameById(Integer customerId);

    /// 通过用户信息查找用户Id
    @Select("select customer_id from customers where cname=#{cname} and identification=#{identification}")
    Integer getCustomerIdByInfo(String cname, String identification);

    /// 获取指定用户信息
    @Select("select * from customers where customer_id=#{customerId}")
    Customers getCustomer(Integer customerId);
}
