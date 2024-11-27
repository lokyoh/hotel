package com.lokyoh.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.mapper.UserMapper;
import com.lokyoh.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageBean<Customers> list(Integer pageNum, Integer pageSize, String phone, String level) {
        PageBean<Customers> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Customers> rs = userMapper.list(phone, level);
        PageInfo<Customers> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }
}
