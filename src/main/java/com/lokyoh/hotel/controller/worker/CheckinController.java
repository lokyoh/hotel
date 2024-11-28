package com.lokyoh.hotel.controller.worker;

import com.lokyoh.hotel.entity.Checkins;
import com.lokyoh.hotel.entity.PageBean;
import com.lokyoh.hotel.entity.RCInfo;
import com.lokyoh.hotel.entity.Result;
import com.lokyoh.hotel.service.CheckinService;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("worker/checkin")
@Validated
public class CheckinController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CheckinService checkinService;

    @PutMapping("/add")
    public Result<String> add(@RequestBody Checkins checkins) {
        if (checkins.getCheckinTime().isAfter(checkins.getDepartureTime())) return Result.error("时间错误");
        if (roomService.checkRoom(checkins.getRoomId(), checkins.getCheckinTime(), checkins.getDepartureTime())) {
            checkinService.add(checkins);
            return Result.success();
        }
        return Result.error("房间已被占用");
    }

    @GetMapping("/list")
    public Result<PageBean<RCInfo>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) String status
    ) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) return Result.error("pageNum错误");
        if (pageSize > 30 || pageSize < 1) return Result.error("pageSize错误");
        PageBean<RCInfo> pb = checkinService.list(pageNum, pageSize, phone, roomType, status);
        return Result.success(pb);
    }
}
