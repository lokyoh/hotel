package com.lokyoh.hotel.controller.worker;

import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker/customer")
@Validated
public class CustomerController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<PageBean<Customers>> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String level
    ) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) return Result.error("pageNum错误");
        if (pageSize > 30 || pageSize < 1) return Result.error("pageSize错误");
        PageBean<Customers> pb = userService.list(pageNum, pageSize, phone, level);
        return Result.success(pb);
    }

    @PutMapping("/add")
    public Result<String> add(@RequestBody Customers customers) {
        return Result.success();
    }

    @PutMapping("/modify")
    public Result<String> modify(@RequestBody Customers customers) {
        return Result.success();
    }

    @PostMapping("/register")
    public Result<String> register(@RequestParam Integer customerId) {
        return Result.success();
    }
}
