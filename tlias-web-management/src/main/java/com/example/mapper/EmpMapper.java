package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    //查询总记录数
    @Select("select count(*) from emp")
    public Long count();

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */

    @Select("select e.*,d.name  deptName from emp e left join dept d on e.dept_id =d.id order by e.update_time desc limit #{start},#{pageSize} ")
    public List<Emp> list(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

}
