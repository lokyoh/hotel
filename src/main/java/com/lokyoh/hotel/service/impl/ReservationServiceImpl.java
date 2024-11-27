package com.lokyoh.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.PageBean;
import com.lokyoh.hotel.entity.RCInfo;
import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.mapper.ReservationMapper;
import com.lokyoh.hotel.mapper.RoomMapper;
import com.lokyoh.hotel.mapper.UserMapper;
import com.lokyoh.hotel.service.ReservationService;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(Reservations reservations) {
        String type = roomMapper.getRoomType(reservations.getRoomId());
        reservations.setRtype(type);
        reservationMapper.add(reservations);
        Occupancies occupancies = new Occupancies(reservations);
        roomMapper.addOccupancy(occupancies);
    }

    @Override
    public Reservations get(Integer reservationId, Integer customerId) {
        return reservationMapper.get(reservationId, customerId);
    }

    @Override
    public void modify(Reservations oldReservations, Reservations reservations) {
        Occupancies oldOccupancies = new Occupancies(oldReservations);
        Occupancies occupancies = new Occupancies(reservations);
        roomService.modifyOccupancy(oldOccupancies, occupancies);
        reservations.setRtype(roomMapper.getRoomType(reservations.getRoomId()));
        reservationMapper.modify(reservations);
    }

    @Override
    public void cancel(Reservations reservation) {
        Integer occupancyId = roomMapper.getOccupancyId(reservation.getRoomId(), reservation.getExpectedCheckin());
        roomMapper.delOccupancy(occupancyId);
        reservationMapper.cancel(reservation.getReservationId());
    }

    @Override
    public Integer getOccupancyId(Integer id, Integer customerId) {
        Reservations reservation = get(id, customerId);
        return roomMapper.getOccupancyId(reservation.getRoomId(), reservation.getExpectedCheckin());
    }

    @Override
    public void checkout(Integer reservationId) {
        reservationMapper.checkout(reservationId);
    }

    @Override
    public PageBean<RCInfo> list(Integer pageNum, Integer pageSize, String phone, String roomType, String status) {
        PageBean<RCInfo> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        Integer customerId = phone == null ? null : userMapper.getCustomerIdByPhone(phone);
        List<Reservations> rs = reservationMapper.list(customerId, roomType, status);
        PageInfo<Reservations> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(
                p.getList().stream().map(
                        reservation -> {
                            String identification = userMapper.getIdentification(reservation.getCustomerId());
                            return new RCInfo(reservation, identification);
                        }
                ).toList()
        );
        return pb;
    }
}
