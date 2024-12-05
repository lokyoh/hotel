package com.lokyoh.hotel.controller.admin;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.entity.Employees;
import com.lokyoh.hotel.entity.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/worker")
@Validated
public class WorkersController {
    @GetMapping("/list")
    public Result<Object> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ) {
        //TODO
        return Result.success();
    }

    @PutMapping("/add")
    public Result<String> add(@RequestBody Employees employee) {
        //TODO
        return Result.success();
    }

    @PutMapping("/modify")
    public Result<String> modify(@RequestParam Employees employee) {
        //TODO
        return Result.success();
    }

    @PostMapping("/del")
    public Result<String> del(@RequestParam Integer id) {
        //TODO
        return Result.success();
    }

    @PutMapping("/account/add")
    public Result<String> addAccount(@RequestBody Account account) {
        //TODO
        return Result.success();
    }

    @PostMapping("/account/del")
    public Result<String> delAccount(@RequestParam Integer id) {
        //TODO
        return Result.success();
    }
}
