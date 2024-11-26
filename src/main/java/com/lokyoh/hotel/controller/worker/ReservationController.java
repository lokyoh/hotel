package com.lokyoh.hotel.controller.worker;

import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.entity.Result;
import com.lokyoh.hotel.service.ReservationService;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("worker/reservation")
@Validated
public class ReservationController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add")
    public Result<String> newReservation(@RequestBody Reservations reservations) {
        if(reservations.getExpectedCheckin().isAfter(reservations.getExpectedCheckout())) return Result.error("时间错误");
        if (roomService.checkRoom(reservations.getRoomId(), reservations.getExpectedCheckin(), reservations.getExpectedCheckout())) {
            reservationService.add(reservations);
            return Result.success();
        }
        return Result.error("房间已被占用");
    }

    @PostMapping("/modify")
    public Result<String> modifyReservation(@RequestBody Reservations reservations) {
        if(reservations.getExpectedCheckin().isAfter(reservations.getExpectedCheckout())) return Result.error("时间错误");
        Reservations oldReservation = reservationService.get(reservations.getReservationId(), reservations.getCustomerId());
        if (oldReservation == null) return Result.error("找不到指定预定单");
        if (oldReservation.getRstatus().equals("已完成")) return Result.error("指定账单无法修改");
        try{
            reservationService.modify(oldReservation, reservations);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<String> cancelReservation(Integer reservationId, Integer customerId) {
        Reservations reservation = reservationService.get(reservationId, customerId);
        if (reservation == null) return Result.error("找不到指定预定单");
        if (!reservation.getRtype().equals("未完成")) return Result.error("此账单无法取消");
        reservationService.cancel(reservation);
        return Result.success();
    }
}
