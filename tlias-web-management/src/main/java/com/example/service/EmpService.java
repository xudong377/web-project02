package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
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
}
