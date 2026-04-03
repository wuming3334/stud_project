package com.itwu.Service.impl;

import com.itwu.Mapper.DeptMapper;
import com.itwu.Service.DeptService;
import com.itwu.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalTime.now;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }
    @Override
    public Integer deleteById(Integer id){
        Integer i = deptMapper.deleteById(id);
        return i;
    }

    @Override
    public void insertDept(Dept dept){
        //补充基础字段的值
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper
        deptMapper.insertDept(dept);
    }

    /**
     *
     * 方式一:我自己写的
     * @Override
     *     public Dept updateDept(Integer id,String name){
     *         Dept findDept = deptMapper.findById(id);
     *         findDept.setName(name);
     *         findDept.setUpdateTime(LocalDateTime.now());
     *         deptMapper.updateDept(findDept);
     *         return findDept;
     *     }
     *
     *     方式二:
     */
    @Override
    public Integer updateDept(Dept dept){
        Dept findDept = deptMapper.findById(dept.getId());
        findDept.setName(dept.getName());
        findDept.setUpdateTime(LocalDateTime.now());
        Integer i = deptMapper.updateDept(findDept);
        return i;
    }
    @Override
    public Dept findById(Integer id){
        //查询回显
        return deptMapper.findById(id);
    }
}
