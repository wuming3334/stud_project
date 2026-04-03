package com.itwu.Controller.impl;

import com.itwu.Controller.DeptController;
import com.itwu.Service.DeptService;
import com.itwu.pojo.Dept;
import com.itwu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptControllerImpl implements DeptController {
    @Autowired
    private DeptService deptService;
    /* @RequestMapping(value = "/depts",method = RequestMethod.GET)*/

    /**
     * 企业常用@GetMapping注释替代
     */
    @GetMapping
    @Override
    public Result list() {
       /* System.out.println("查询全部部门数据");*/
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        if (deptList != null) {
            return Result.success(deptList);
        } else return Result.error("无数据");
    }
    /**
     * 删除部门
     *
     *方式一:使用HttpServletRequest
     */

    /*@Override
    public Result deleteDept(HttpServletRequest request) {
      String idStr = request.getParameter("id");
      return deptService.deleteById(Integer.parseInt(idStr)) > 0
              ? Result.success() : Result.error("删除失败");
    }
    @DeleteMapping("/depts")*/

    /**
     *方式二:使用@RequestParam
     * 注意事项:若使用了@RequestParam注解,则必须传递参数,否则会报错,因为required默认=ture
     * 如果想要忽略参数,则required=false即可
     * @RequestParam(value = "id",required = false)
     * 若请求参数名与形参名相同 则可以省略@RequestParam注解,参考方式3
     */
   /* @DeleteMapping("/depts")
    @Override
    public Result deleteDept(@RequestParam(value = "id",required = false) Integer deptId) {
        System.out.println("删除部门:"+ deptId);
        Integer i = deptService.deleteById(deptId);
        return i > 0 ? Result.success() : Result.error("删除失败");
    }*/

    /**
     * 方式3:
     */
    @DeleteMapping
    @Override
    public Result deleteDept(Integer id) {
        /*System.out.println("删除部门:" + id);*/
        log.info("删除部门:" + id);
        Integer i = deptService.deleteById(id);
        return i > 0 ? Result.success() : Result.error("删除失败");
    }

    @PostMapping
    @Override
    /**
     * 添加部门
     * @RequestBody是用来让前端发送的json格式数据 转换成对象的属性
     * 注意事项:必须保持对象的属性名与json数据中的键名一致,否则赋值不了
     */
    public Result insertDept(@RequestBody Dept dept) {
        /*System.out.println("添加部门:" + dept);*/
        log.info("添加部门:" + dept);
        deptService.insertDept(dept);
        return dept == null ? Result.error("添加失败") : Result.success();
    }

    @PutMapping
    @Override
    public Result updateDept(@RequestBody Dept dept) {
       /* System.out.println("修改部门:" + dept.getName());*/
        log.info("修改部门:" + dept.getName());
        Integer i = deptService.updateDept(dept);
        return  i > 0 ?  Result.success() : Result.error("修改失败");
    }

    /**
     * 查询部门
     * 在路径由占位符接手参数的情况下 需要使用@PathVariable注解来指定接受的参数
     * 必须保证占位符的名称与注解形参名称一致
     * 若方法形参名与路径占位符名一致 则可省略@PathVariable注解的参数,参考findById方法
     */

    @GetMapping("/{id}")
    @Override
    public Result findById(@PathVariable Integer id) {
        /*System.out.println("查询部门:" + id);*/
        log.info("查询部门:" + id);
        Dept dept = deptService.findById(id);
        return dept == null ? Result.error("无此部门") : Result.success(dept);
    }
}
