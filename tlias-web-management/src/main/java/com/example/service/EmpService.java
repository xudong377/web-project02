package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageResult;

public interface EmpService {

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<Emp> page(Integer page, Integer pageSize);
}
