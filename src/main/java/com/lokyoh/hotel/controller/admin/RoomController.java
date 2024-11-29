package com.lokyoh.hotel.controller.admin;

import com.lokyoh.hotel.entity.Result;
import com.lokyoh.hotel.entity.Room;
import com.lokyoh.hotel.entity.RoomType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/room")
@Validated
public class RoomController {
    @GetMapping("/list")
    public Result<String> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ) {
        //TODO
        return Result.success();
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
