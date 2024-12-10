package com.lokyoh.hotel.controller.admin;

import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/room")
@Validated
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public Result<Object> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String type
    ) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) return Result.error("pageNum错误");
        if (pageSize > 30 || pageSize < 1) return Result.error("pageSize错误");
        //TODO
        PageBean<Room> pb = roomService.roomList(pageNum,pageSize,type);
        return Result.success(pb);
    }

    @PutMapping("/add")
    public Result<String> add(@RequestBody Room room) {
        //TODO
        return Result.success();
    }

    @PutMapping("/modify")
    public Result<String> modify(@RequestParam Room room) {
        //TODO
        return Result.success();
    }

    @PostMapping("/del")
    public Result<String> del(@RequestParam Integer id) {
        //TODO
        return Result.success();
    }

    @PutMapping("/type/add")
    public Result<String> addType(@RequestBody RoomType roomType) {
        //TODO
        return Result.success();
    }

    @PostMapping("/type/del")
    public Result<String> delType(@RequestParam String type) {
        //TODO
        return Result.success();
    }
}
