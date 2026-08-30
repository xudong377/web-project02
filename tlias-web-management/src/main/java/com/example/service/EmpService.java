package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;

public interface EmpService {

    /**
     * 分页查询
     * @return
     */
    /*PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
                         LocalDate begin,
                         LocalDate end);*/

    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
