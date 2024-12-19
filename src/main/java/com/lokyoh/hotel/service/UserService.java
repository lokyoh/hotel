package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.Customers;
import com.lokyoh.hotel.entity.PageBean;

public interface UserService {
    /// 通过用户信息查找用户Id
    Integer getCustomerIdByInfo(String cname, String identification);

    /// 获取指定用户信息
    Customers getCustomer(Integer userId);

    PageBean<Customers> list(Integer pageNum, Integer pageSize, String phone, String level);

    void add(Customers customers);

    void modify(Customers customers);

    void register(Integer customerId);
}
