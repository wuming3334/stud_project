package com.itwu.Controller;

import com.itwu.pojo.Dept;
import com.itwu.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface DeptController {
    public Result list();

    /*public Result deleteDept(HttpServletRequest  request);*/
    public Result deleteDept(Integer id);

    public Result insertDept(Dept dept);

        Result updateDept(Dept dept);

    Result findById(Integer id);
}
