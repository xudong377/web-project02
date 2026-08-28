package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询所有部门数据
     * @return
     */
    List<Dept> findAll();

    void deleteById(Integer id);
}
