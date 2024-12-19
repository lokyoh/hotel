package com.lokyoh.hotel.mapper;

import com.lokyoh.hotel.entity.Account;
import com.lokyoh.hotel.entity.Employees;
import org.apache.ibatis.annotations.*;

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

    @Update("update employees set ename=#{ename},identification=#{identification},gender=#{gender},birth_date=#{birthDate},department_name=#{departmentName},contact_address=#{contactAddress},phone_number=#{phoneNumber},email=#{email}" +
            " where employee_id=#{employeeId}")
    void modify(Employees employee);

    @Delete("delete from employees where employee_id=#{id}")
    void del(Integer id);

    @Insert("insert into account (employee_id, account, password, level)" +
            " values (#{employeeID}, #{account}, #{password}, #{level})")
    void addAccount(Account account);

    @Delete("delete from account where employee_id=#{employeeId}")
    void delAccount(Integer id);
}
