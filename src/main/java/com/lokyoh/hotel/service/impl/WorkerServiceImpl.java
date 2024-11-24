package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Room;
import com.lokyoh.hotel.entity.RoomType;
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
    public List<Room> getRooms() {
        return workerMapper.getRooms();
    }

    @Override
    public List<Occupancies> getNowOccupy() {
        return workerMapper.getNowOccupy();
    }

    @Override
    public String getCustomerNameById(Integer customerId) {
        return workerMapper.getCustomerNameById(customerId);
    }
}
