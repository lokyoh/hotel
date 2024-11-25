package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.Customers;
import com.lokyoh.hotel.mapper.UserMapper;
import com.lokyoh.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer getCustomerIdByInfo(String cname, String identification) {
        return userMapper.getCustomerIdByInfo(cname, identification);
    }

    @Override
    public Customers getCustomer(Integer customerId) {
        return userMapper.getCustomer(customerId);
    }
}
