package com.itwu.Mapper;


import com.itwu.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门数据
     */
    /**
     *
     * 解决因属性名与字段名不同导致没有自动封装结果的方式一:手动结果映射
     *
     *  @Results({
     *             @Result(column = "create_time", property = "createTime"),
     *             @Result(column = "update_time", property = "updateTime")
     *     })
     * 方式2:起别名即可 可在映射中指定别名,也可以@select 指定别名
     */

    public List<Dept> findAll();
    /**
     * 删除部门
     *
     */
    public Integer deleteById(Integer id);
    public void insertDept(Dept dept);

    public Integer updateDept(Dept dept);
    public Dept findById(Integer id);
}
