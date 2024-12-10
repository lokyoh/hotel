package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.*;

public interface WorkerService {
    /// 根据员工账号寻找员工账户数据
    Account findByAccount(String account);

    PageBean<Employees> list(Integer pageNum, Integer pageSize, String phone, String departmentName);

    void add(Employees employee);

    void modify(Employees employee);

    void del(Integer id);
}
