package com.lokyoh.hotel.controller.worker;

import com.lokyoh.hotel.entity.PageBean;
import com.lokyoh.hotel.entity.RCInfo;
import com.lokyoh.hotel.entity.Reservations;
import com.lokyoh.hotel.entity.Result;
import com.lokyoh.hotel.service.ReservationService;
import com.lokyoh.hotel.service.RoomService;
import com.lokyoh.hotel.utils.PageCheckerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("worker/reservation")
@Validated
public class ReservationController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private ReservationService reservationService;

    @PutMapping("/add")
    public Result<String> newReservation(@RequestBody Reservations reservations) {
        if (reservations.getExpectedCheckin().isAfter(reservations.getExpectedCheckout()))
            return Result.error("时间错误");
        if (roomService.checkRoom(reservations.getRoomId(), reservations.getExpectedCheckin(), reservations.getExpectedCheckout())) {
            reservationService.add(reservations);
            return Result.success();
        }
        return Result.error("房间已被占用");
    }

    @PutMapping("/modify")
    public Result<String> modifyReservation(@RequestBody Reservations reservations) {
        if (reservations.getExpectedCheckin().isAfter(reservations.getExpectedCheckout()))
            return Result.error("时间错误");
        Reservations oldReservation = reservationService.get(reservations.getReservationId(), reservations.getCustomerId());
        if (oldReservation == null) return Result.error("找不到指定预定单");
        if (!oldReservation.getRstatus().equals("未完成")) return Result.error("指定账单无法修改");
        try {
            reservationService.modify(oldReservation, reservations);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result<String> cancelReservation(
            @RequestParam Integer reservationId,
            @RequestParam Integer customerId
    ) {
        Reservations reservation = reservationService.get(reservationId, customerId);
        if (reservation == null) return Result.error("找不到指定预定单");
        if (!reservation.getRstatus().equals("未完成")) return Result.error("此账单无法取消");
        reservationService.cancel(reservation);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<PageBean<RCInfo>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) String status
    ) {
        PageCheckerUtil.checkPage(pageNum, pageSize);
        PageBean<RCInfo> pb = reservationService.list(pageNum, pageSize, phone, roomType, status);
        return Result.success(pb);
    }
}
