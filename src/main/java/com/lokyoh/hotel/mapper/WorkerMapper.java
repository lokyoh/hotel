package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkerMapper {
    /// 查询指定员工账户
    @Select("select * from account where account=#{account}")
    Account findByAccount(String account);
}
