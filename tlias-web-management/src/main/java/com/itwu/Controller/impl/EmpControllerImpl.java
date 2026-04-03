package com.itwu.Controller.impl;

import com.itwu.Controller.EmpController;
import com.itwu.Service.EmpService;
import com.itwu.pojo.Emp;
import com.itwu.pojo.EmpQueryParam;
import com.itwu.pojo.PageResult;
import com.itwu.pojo.Result;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpControllerImpl implements EmpController {

    @Autowired
    private EmpService empService;


    /**
     * @RequestParam(defaultValue = "1")====指定默认值
     * @DateTimeFormat(pattern = "yyyy-MM-dd")=====指定时间格式,因为前端传递的格式多种多样
     */
    /*public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                        String name,
                        Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pr = empService.page(page, pageSize, name, gender, begin, end);
        return pr == null ? Result.error("未查询到数据") : Result.success(pr);
    }*/

    /**
     * 若参数较多,则使用对象接收参数,否则可以在方法中使用形参接收参数,这样做较为简洁
     */
    @GetMapping
    public Result list(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pr = empService.page(empQueryParam);
        return pr == null ? Result.error("未查询到数据") :
                Result.success(pr);
    }
    @PostMapping
    public Result add(@RequestBody Emp  emp){
        empService.add(emp);
        return Result.success();
    }


}
