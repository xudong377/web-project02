package com.example.mapper;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    /**
     * 条件查询员工列表（分页由 PageHelper 自动处理，无需手动传 start/pageSize）
     * @param empQueryParam 查询条件（name/gender/begin/end）
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) VALUES (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据ID批量删除员工基本信息
     * @param ids
     */

    void deleteByIds(@Param("ids") List<Integer> ids);
}
