package com.lokyoh.hotel.service;

import com.lokyoh.hotel.entity.*;

public interface WorkerService {
    /// 根据员工账号寻找员工账户数据
    Account findByAccount(String account);
}
