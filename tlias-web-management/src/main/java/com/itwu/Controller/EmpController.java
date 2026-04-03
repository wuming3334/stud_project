package com.itwu.Controller;

import com.itwu.pojo.Emp;
import com.itwu.pojo.EmpQueryParam;
import com.itwu.pojo.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController

public interface EmpController {
    public Result list(EmpQueryParam empQueryParam);
    public Result add(Emp emp);

}
