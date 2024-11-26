package com.lokyoh.hotel.service.impl;

import com.lokyoh.hotel.entity.Occupancies;
import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.mapper.ReservationMapper;
import com.lokyoh.hotel.mapper.RoomMapper;
import com.lokyoh.hotel.service.ReservationService;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private RoomService roomService;

    @Override
    public void add(Reservations reservations) {
        reservations.setRtype(roomMapper.getRoomType(reservations.getRoomId()));
        reservationMapper.add(reservations);
        Occupancies occupancies = new Occupancies();
        occupancies.setCustomerId(reservations.getCustomerId());
        occupancies.setRoomId(reservations.getRoomId());
        occupancies.setStartTime(reservations.getExpectedCheckin());
        occupancies.setEndTime(reservations.getExpectedCheckout());
        roomMapper.addOccupancy(occupancies);
    }

    @Override
    public Reservations get(Integer reservationId, Integer customerId) {
        return reservationMapper.get(reservationId, customerId);
    }

    @Override
    public void modify(Reservations oldReservations, Reservations reservations) {
        Occupancies oldOccupancies = new Occupancies();
        oldOccupancies.setCustomerId(oldReservations.getCustomerId());
        oldOccupancies.setRoomId(oldReservations.getRoomId());
        oldOccupancies.setStartTime(oldReservations.getExpectedCheckin());
        oldOccupancies.setEndTime(oldReservations.getExpectedCheckout());
        Occupancies occupancies = new Occupancies();
        occupancies.setCustomerId(reservations.getCustomerId());
        occupancies.setRoomId(reservations.getRoomId());
        occupancies.setStartTime(reservations.getExpectedCheckin());
        occupancies.setEndTime(reservations.getExpectedCheckout());
        roomService.modifyOccupancy(oldOccupancies, occupancies);
        reservations.setRtype(roomMapper.getRoomType(reservations.getRoomId()));
        reservationMapper.modify(reservations);
    }

    @Override
    public void cancel(Reservations reservation) {
        roomMapper.delOccupancy(1);
        reservationMapper.cancel(reservation.getReservationId());
    }
}
