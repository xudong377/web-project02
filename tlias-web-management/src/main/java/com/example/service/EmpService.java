package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.Logininfo;
import com.example.pojo.PageResult;

import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     * @return
     */
    /*PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
                         LocalDate begin,
                         LocalDate end);*/

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    Logininfo login(Emp emp);
}
