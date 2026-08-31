package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     * @return
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("{},",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);

    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("{},",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工
     */
    /*@DeleteMapping
    public Result delete(Integer[] ids) {
        log.info("{},", Arrays.toString(ids));
        return Result.success();
    }*/

    /**
     * 删除员工-List
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("{},",ids);
        empService.delete(ids);
        return Result.success();
    }
}
