package com.itwu.Mapper;

import com.itwu.pojo.Emp;
import com.itwu.pojo.EmpExpr;
import com.itwu.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {


    public List<Emp> list(EmpQueryParam empQueryParam);
    //若不是在mapper接口中定义sql语句,
    // 则需要在mapper.xml文件中使用Options标签指定
    /*@Options(useGeneratedKeys = true, keyProperty = "id")*/
    void add(Emp emp);

    /**
     * 原始分页查询的实现方式
     */
    //分页查询 给定起始索引和每页记录数#{start} #{pageSize},映射java.util.List<Emp>
   /* v(Integer start, Integer pageSize);
   //获取总记录数 使用sql聚合函数获取 在mapper.xml文件中给定映射关系 映射java.lang.Integer
    public Integer getEmpCount( );
*/


}
