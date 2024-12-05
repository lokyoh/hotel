package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.entity.Employees;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkerMapper {
    /// 查询指定员工账户
    @Select("select * from account where account=#{account}")
    Account findByAccount(String account);

    List<Employees> list(String phone, String departmentName);

    @Insert("insert into employees (ename, identification, gender, birth_date, hire_date, department_name, contact_address, phone_number, email)" +
            " values (#{ename}, #{identification}, #{gender}, #{birthDate}, #{hireDate}, #{departmentName}, #{contactAddress}, #{phoneNumber}, #{email})")
    void add(Employees employee);
}
