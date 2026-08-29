package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
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
    @DeleteMapping
    public Result delete(Integer id) {
        System.out.println("根据id删除部门" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("新增部门：" + dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     */
    /*@GetMapping("/depts/{id}")
    public Result get(@PathVariable("id") Integer deptid){
        System.out.println("要查询的部门: "+deptid);
        return Result.success();
    }*/
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        System.out.println("要查询的部门: " + id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门： " + dept);
        deptService.update(dept);
        return Result.success();
    }
}
