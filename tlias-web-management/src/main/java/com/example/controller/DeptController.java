package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门：方式一：@RequestParam
     */
    /*@DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId){
        System.out.println("根据id删除部门"+deptId);
        return Result.success();
    }*/

    /**
     * 方式二：省略@RequestParam 请求参数名与形参变量名相同，直接接收
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("根据id删除部门"+id);
        deptService.deleteById(id);
        return Result.success();
    }
}
