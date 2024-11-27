package com.lokyoh.hotel.controller.worker;

import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.service.UserService;
import com.lokyoh.hotel.utils.PageCheckerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        PageCheckerUtil.checkPage(pageNum, pageSize);
        PageBean<Customers> pb = userService.list(pageNum, pageSize, phone, level);
        return Result.success(pb);
    }
}
