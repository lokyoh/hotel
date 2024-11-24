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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
        Rooms rooms = new Rooms();

        // 获取房间
        List<Room> roomList = roomService.getRooms();
        rooms.setCount(roomList.size());

        // 获取入住,预定表
        List<Occupancies> occupanciesList = roomService.getNowOccupy();
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
                }else {
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
                        customer = userService.getCustomerNameById(checkInRooms.get(roomId).getCustomerId());
                    }else if(reserveRooms.containsKey(roomId)) {
                        status = 2;
                        customer = userService.getCustomerNameById(reserveRooms.get(roomId).getCustomerId());
                    }
                    return new RoomInfo(roomId, room.getRoomId(), status, customer);
                }).toList();
        rooms.setRooms(roomInfoList);

        return Result.success(rooms);
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
    public Result<String> newCheckin(Checkins checkins) {
        return Result.error("房间已被占用");
    }
}
