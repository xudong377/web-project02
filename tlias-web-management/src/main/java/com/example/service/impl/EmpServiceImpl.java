package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数（PageHelper 会自动给下一条 SQL 加 limit，并自动执行 count 查询）
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2. 执行条件查询（无需手动拼 limit，也无需单独的 count 方法）
        List<Emp> rows = empMapper.list(empQueryParam.getName(), empQueryParam.getGender(),
                empQueryParam.getBegin(), empQueryParam.getEnd());

        //3. 把结果强转为 Page，拿到总记录数
        Page<Emp> page = (Page<Emp>) rows;

        //4. 封装结果
        return new PageResult<>(page.getTotal(), page.getResult());
    }
}
