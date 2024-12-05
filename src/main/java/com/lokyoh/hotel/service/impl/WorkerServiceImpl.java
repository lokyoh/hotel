package com.lokyoh.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.mapper.WorkerMapper;
import com.lokyoh.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public Account findByAccount(String account) {
        return workerMapper.findByAccount(account);
    }

    @Override
    public PageBean<Employees> list(Integer pageNum, Integer pageSize, String phone, String departmentName) {
        PageBean<Employees> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Employees> rs = workerMapper.list(phone, departmentName);
        PageInfo<Employees> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }

    @Override
    public void add(Employees employee) {
        workerMapper.add(employee);
    }
}
