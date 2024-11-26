package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.Checkins;
import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.mapper.CheckinMapper;
import com.lokyoh.hotel.mapper.RoomMapper;
import com.lokyoh.hotel.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckinServiceImpl implements CheckinService {
    @Autowired
    private CheckinMapper checkinMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public void add(Checkins checkins) {
        checkins.setPreid("null");
        checkinMapper.add(checkins);
        Occupancies occupancies = new Occupancies();
        occupancies.setCustomerId(checkins.getCustomerId());
        occupancies.setRoomId(checkins.getRoomId());
        occupancies.setStartTime(checkins.getCheckinTime());
        occupancies.setEndTime(checkins.getDepartureTime());
        roomMapper.addOccupancy(occupancies);
    }
}
