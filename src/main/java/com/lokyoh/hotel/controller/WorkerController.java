package com.lokyoh.hotel.controller;

import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.service.RoomService;
import com.lokyoh.hotel.service.UserService;
import com.lokyoh.hotel.service.WorkerService;
import com.lokyoh.hotel.utils.JwtUtil;
import com.lokyoh.hotel.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/worker")
@Validated
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "\\S{5,16}$") String account, @Pattern(regexp = "\\S{5,16}$") String password) {
        Account worker = workerService.findByAccount(account);
        if (worker == null) {
            return Result.error("用户名错误");
        }

        if (worker.getPassword().equals(password)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("type", "worker");
            claims.put("account", worker.getAccount());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
    }

    @GetMapping("/info")
    public Result<Object> info() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String type = (String) map.get("type");

        if (type.equals("worker")) {
            String account = (String) map.get("account");
            Account worker = workerService.findByAccount(account);
            return Result.success(worker);
        } else {
            return Result.error("无权限");
        }
    }

    @GetMapping("/rooms")
    public Result<Object> getRooms() {
        return Result.success(roomService.getRooms());
    }

    @PostMapping("/newReservation")
    public Result<String> newReservation(@RequestBody Reservations reservations) {
        if (roomService.checkRoom(reservations.getRoomId())) {
            roomService.newReservation(reservations);
            return Result.success();
        }
        return Result.error("房间已被占用");
    }

    @PostMapping("/newCheckin")
    public Result<String> newCheckin(@RequestBody Checkins checkins) {
        if (roomService.checkRoom(checkins.getRoomId())) {
            roomService.newCheckin(checkins);
            return Result.success();
        }
        return Result.error("房间已被占用");
    }

    @PostMapping("/userId")
    public Result<Object> getUserId(String cname, String identification){
        Integer userId = userService.getCustomerIdByInfo(cname, identification);
        if (userId == null) {
            return Result.error("找不到指定信息");
        }
        return Result.success(userId);
    }

    @PostMapping("/roomInfo")
    public Result<Object> getRoomInfo(String roomId){
        RoomDetail detail = roomService.getOccupyInfo(roomId);
        if (detail == null) {
            return Result.error("未找到指定房间");
        }
        return Result.success(detail);
    }

    @PostMapping("/userInfo")
    public Result<Object> getUserInfo(Integer customerId){
        Customers customer = userService.getCustomer(customerId);
        if (customer == null) {
            return Result.error("找不到指定用户");
        }
        return Result.success(customer);
    }
}
