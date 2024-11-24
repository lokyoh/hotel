package com.lokyoh.hotel.service;

public interface UserService {
    /// 通过用户ID查找用户名
    String getCustomerNameById(Integer customerId);
}
