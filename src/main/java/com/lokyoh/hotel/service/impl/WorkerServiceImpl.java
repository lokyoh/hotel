package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.mapper.WorkerMapper;
import com.lokyoh.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public Account findByAccount(String account) {
        return workerMapper.findByAccount(account);
    }
}
