package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.mapper.RoomMapper;
import com.lokyoh.hotel.mapper.UserMapper;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Rooms getRooms() {
        Rooms rooms = new Rooms();

        // 获取房间
        List<Room> roomList = roomMapper.getRooms();
        rooms.setCount(roomList.size());

        // 获取入住,预定表
        List<Occupancies> occupanciesList = roomMapper.getAllOccupy();
        Map<String, Occupancies> checkInRooms = new HashMap<>();
        Map<String, Occupancies> reserveRooms = new HashMap<>();
        occupanciesList.forEach(occupancy -> {
            String roomId = occupancy.getRoomId();
            if (!checkInRooms.containsKey(roomId)) {
                if (!occupancy.getStartTime().isAfter(LocalDate.now())) {
                    checkInRooms.put(roomId, occupancy);
                } else if (reserveRooms.containsKey(roomId)) {
                    if (reserveRooms.get(roomId).getStartTime().isAfter(occupancy.getStartTime())) {
                        reserveRooms.put(roomId, occupancy);
                    }
                } else {
                    reserveRooms.put(roomId, occupancy);
                }
            }
        });

        // 填入房间数据
        List<RoomInfo> roomInfoList = roomList.stream()
                .map(room -> {
                    String roomId = room.getRoomId();
                    int status = 0;
                    String customer = null;
                    if (checkInRooms.containsKey(roomId)) {
                        status = 1;
                        customer = userMapper.getCustomerNameById(checkInRooms.get(roomId).getCustomerId());
                    } else if (reserveRooms.containsKey(roomId)) {
                        status = 2;
                        customer = userMapper.getCustomerNameById(reserveRooms.get(roomId).getCustomerId());
                    }
                    return new RoomInfo(roomId, room.getRoomId(), status, customer);
                }).toList();
        rooms.setRooms(roomInfoList);
        return rooms;
    }

    @Override
    public boolean checkRoom(String roomId, LocalDate startTime, LocalDate endTime) {
        return roomMapper.getRoomHasOccupied(roomId, startTime, endTime).isEmpty();
    }

    @Override
    public RoomDetail getOccupyInfo(String roomId) {
        Occupancies occupancy = roomMapper.getResentOccupancies(roomId);
        if (occupancy == null) {
            return null;
        }
        RoomDetail roomDetail = new RoomDetail();
        roomDetail.setRoomId(occupancy.getRoomId());
        roomDetail.setCustomerId(occupancy.getCustomerId());
        roomDetail.setStartTime(occupancy.getStartTime());
        roomDetail.setEndTime(occupancy.getEndTime());
        roomDetail.setType(roomMapper.getRoomType(occupancy.getRoomId()));
        List<String> resident = new ArrayList<>();
        resident.add(userMapper.getCustomerNameById(occupancy.getCustomerId()));
        roomMapper.getCohabitUserId(occupancy.getOccupancyId()).forEach(customerId -> resident.add(userMapper.getCustomerNameById(customerId)));
        roomDetail.setResident(resident);
        return roomDetail;
    }

    @Override
    public void modifyOccupancy(Occupancies oldOccupancies, Occupancies occupancies) {
        if (oldOccupancies.getRoomId().equals(occupancies.getRoomId())) {
            roomMapper.getOccupyByRoomId(oldOccupancies.getRoomId()).forEach(occupancy -> {
                if (!occupancy.getStartTime().isEqual(oldOccupancies.getStartTime())) {
                    if (!(occupancy.getStartTime().isAfter(occupancies.getEndTime()) || occupancy.getEndTime().isBefore(occupancies.getStartTime())))
                        throw new RuntimeException("房间已被占用");
                }
            });
        } else {
            if (!roomMapper.getRoomHasOccupied(occupancies.getRoomId(), occupancies.getStartTime(), occupancies.getEndTime()).isEmpty())
                throw new RuntimeException("房间已被占用");
        }
        roomMapper.modifyOccupancy(oldOccupancies, occupancies);
    }

    @Override
    public void addCohabit(Integer occupancyId, Integer customerId) {
        roomMapper.addCohabit(occupancyId, customerId);
    }

    @Override
    public void delCohabit(Integer occupancyId, Integer customerId) {
        roomMapper.delCohabit(occupancyId, customerId);
    }
}
