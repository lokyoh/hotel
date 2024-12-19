package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Customers;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /// 获取指定用户的id
    @Select("select identification from customers where customer_id=#{customerId}")
    String getIdentification(@NotNull Integer customerId);

    /// 根据手机号获取用户ID
    @Select("select customer_id from customers where phone=#{phone}")
    Integer getCustomerIdByPhone(String phone);

    List<Customers> list(String phone, String level);

    @Insert("insert into customers ()" +
            " values ()")
    void add(Customers customers);

    @Update("update customers set ")
    void modify(Customers customers);

    @Delete("delete from customers where customer_id=#{customerId}")
    void register(Integer customerId);
}
