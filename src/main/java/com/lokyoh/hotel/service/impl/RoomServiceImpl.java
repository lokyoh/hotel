package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.entity.Room;
import com.lokyoh.hotel.mapper.RoomMapper;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> getRooms() {
        return roomMapper.getRooms();
    }

    @Override
    public List<Occupancies> getNowOccupy() {
        return roomMapper.getNowOccupy();
    }

    @Override
    public boolean checkRoom(String roomId) {
        return roomMapper.getRoomOccupied(roomId).isEmpty();
    }

    @Override
    public void newReservation(Reservations reservations) {
        reservations.setRtype(roomMapper.getRoomType(reservations.getRoomId()));
        roomMapper.newReservation(reservations);
        Occupancies occupancies = new Occupancies();
        occupancies.setCustomerId(reservations.getCustomerId());
        occupancies.setRoomId(reservations.getRoomId());
        occupancies.setStartTime(reservations.getExpectedCheckin());
        occupancies.setEndTime(reservations.getExpectedCheckout());
        roomMapper.newOccupancy(occupancies);
    }
}
