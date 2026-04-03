package com.itwu.Service;

import com.itwu.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface DeptService {
    //查询所有部门信息
    public List<Dept> findAll();

    public Integer deleteById(Integer id);

    public void insertDept(Dept dept);

    Integer updateDept(Dept dept);

    Dept findById(Integer id);
}
