package com.lokyoh.hotel.controller.admin;

import com.lokyoh.hotel.entity.*;
import com.lokyoh.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/worker")
@Validated
public class WorkersController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("/list")
    public Result<Object> list(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String departmentName
    ) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) return Result.error("pageNum错误");
        if (pageSize > 30 || pageSize < 1) return Result.error("pageSize错误");
        PageBean<Employees> pb = workerService.list(pageNum, pageSize, phone, departmentName);
        return Result.success(pb);
    }

    @PutMapping("/add")
    public Result<String> add(@RequestBody Employees employee) {
        workerService.add(employee);
        return Result.success();
    }

    @PutMapping("/modify")
    public Result<String> modify(@RequestParam Employees employee) {
        if (employee.getEmployeeId()==null) return Result.error("需要员工id");
//        workerService.modify(employee);
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
