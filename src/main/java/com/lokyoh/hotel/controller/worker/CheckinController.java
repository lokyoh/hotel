package com.lokyoh.hotel.controller.worker;

import com.lokyoh.hotel.entity.Checkins;
import com.lokyoh.hotel.entity.Result;
import com.lokyoh.hotel.service.CheckinService;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("worker/checkin")
@Validated
public class CheckinController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CheckinService checkinService;

    @PostMapping("/add")
    public Result<String> newCheckin(@RequestBody Checkins checkins) {
        if (checkins.getCheckinTime().isAfter(checkins.getDepartureTime())) return Result.error("时间错误");
        if (roomService.checkRoom(checkins.getRoomId(), checkins.getCheckinTime(), checkins.getDepartureTime())) {
            checkinService.add(checkins);
            return Result.success();
        }
        return Result.error("房间已被占用");
    }
}
