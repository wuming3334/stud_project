package com.itwu.Mapper;

import com.itwu.pojo.Emp;
import com.itwu.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    void addExpr(List<EmpExpr> exprList);
}
