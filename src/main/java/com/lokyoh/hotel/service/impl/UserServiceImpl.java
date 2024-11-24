package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.mapper.UserMapper;
import com.lokyoh.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String getCustomerNameById(Integer customerId) {
        return userMapper.getCustomerNameById(customerId);
    }
}
