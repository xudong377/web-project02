package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.service.EmpService;
import com.example.util.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Override
    public Logininfo login(Emp emp) {
        //1.调用Mapper接口，根据用户名和密码查询员工信息
        Emp e=empMapper.selectByUsernameAndPassword(emp);
        //2.判断该员工是否存在，存在，组装登录成功信息
        if(e!=null){
            log.info("登录成功，员工信息:{}",emp);
            //生成JWT令牌
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String token = JwtUtils.generateJwt(claims);

            return new Logininfo(e.getId(),e.getUsername(),e.getName(),token);
        }
        //3.不存在，返回null
        return null;



    }

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数（PageHelper 会自动给下一条 SQL 加 limit，并自动执行 count 查询）
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2. 执行条件查询（无需手动拼 limit，也无需单独的 count 方法）
        List<Emp> rows = empMapper.list(empQueryParam);
        
        //3. 把结果强转为 Page，拿到总记录数
        Page<Emp> page = (Page<Emp>) rows;

        //4. 封装结果
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional // 保证 emp 和 emp_expr 两步插入的原子性，任一失败整体回滚
    public void save(Emp emp) {
        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2.保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empId赋值
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据id修改员工工作经历
        //2.1先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2.2后添加
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }
}
