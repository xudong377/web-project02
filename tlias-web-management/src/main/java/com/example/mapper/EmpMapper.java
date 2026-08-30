package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    /**
     * 条件查询员工列表（分页由 PageHelper 自动处理，无需手动传 start/pageSize）
     * @param name   姓名（模糊）
     * @param gender 性别
     * @param begin  入职日期起
     * @param end    入职日期止
     */
    List<Emp> list(@Param("name") String name,
                   @Param("gender") Integer gender,
                   @Param("begin") LocalDate begin,
                   @Param("end") LocalDate end);
}
