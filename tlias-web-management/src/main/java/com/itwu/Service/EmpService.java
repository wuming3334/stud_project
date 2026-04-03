package com.itwu.Service;

import com.itwu.pojo.Dept;
import com.itwu.pojo.Emp;
import com.itwu.pojo.EmpQueryParam;
import com.itwu.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service

public interface EmpService {
    public PageResult<Emp> page(EmpQueryParam empQueryParam);

    void add(Emp emp);

    /*public Integer getEmpCount();*/
}
