package com.lokyoh.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.mapper.CheckinMapper;
import com.lokyoh.hotel.mapper.RoomMapper;
import com.lokyoh.hotel.mapper.UserMapper;
import com.lokyoh.hotel.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {
    @Autowired
    private CheckinMapper checkinMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(Checkins checkins) {
        checkins.setPreid("null");
        checkinMapper.add(checkins);
        Occupancies occupancies = new Occupancies(checkins);
        roomMapper.addOccupancy(occupancies);
    }

    @Override
    public Checkins get(Integer checkinId, Integer customerId) {
        return checkinMapper.get(checkinId, customerId);
    }

    @Override
    public Integer getOccupancyId(Integer checkinId, Integer customerId) {
        Checkins checkins = get(checkinId, customerId);
        return roomMapper.getOccupancyId(checkins.getRoomId(), checkins.getCheckinTime());
    }

    @Override
    public void cancel(Checkins checkin) {
        Integer occupancyId = roomMapper.getOccupancyId(checkin.getRoomId(), checkin.getCheckinTime());
        roomMapper.delOccupancy(occupancyId);
        checkinMapper.cancel(checkin.getCheckinId());
    }

    @Override
    public void checkout(Integer checkinId) {
        checkinMapper.checkout(checkinId);
    }

    @Override
    public PageBean<RCInfo> list(Integer pageNum, Integer pageSize, String phone, String roomType, String status) {
        PageBean<RCInfo> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        Integer customerId = phone == null ? null : userMapper.getCustomerIdByPhone(phone);
        List<Checkins> rs = checkinMapper.list(customerId, roomType, status);
        PageInfo<Checkins> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(
                p.getList().stream().map(
                        checkin -> {
                            String identification = userMapper.getIdentification(checkin.getCustomerId());
                            String _roomType = roomMapper.getRoomType(checkin.getRoomId());
                            return new RCInfo(checkin, identification, _roomType);
                        }
                ).toList()
        );
        return pb;
    }
}
