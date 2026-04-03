package com.itwu.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itwu.Mapper.EmpExprMapper;
import com.itwu.Mapper.EmpMapper;
import com.itwu.Service.EmpLogService;
import com.itwu.Service.EmpService;
import com.itwu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     * 原始分页查询的操作
     */
  /*  @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //获取总记录数
        Integer empCount = empMapper.getEmpCount();
        System.out.println(empCount);
        //计算起始索引
        Integer start = (page - 1) * pageSize;
        //获取当前页数据
        List<Emp> list = empMapper.list(start, pageSize);
        //封装PageResult对象并返回
        return new PageResult<Emp>(empCount, list);
    }*/

 /*   @Override
    public Integer getEmpCount() {
        return empMapper.getEmpCount();

    }*/
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数,创建pageHelper对象
        //实现原理:拦截sql语句,在sql语句之后添加分页功能
        //注意事项:基于pageHelper的分页功能,sql语句不可加';',否则回导致sql语句错误
        // ,因为pagehelper无法删除分号,导致改造sql语句时会出现语法错误

        //注意2:pageHelper只能对紧跟其后的第一个sql语句进行分页处理
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> list = empMapper.list(empQueryParam);
        //解析查询结果,并封装,然后返回
        //需要强转为Page<Emp>对象,至于为什么能直接强转为list,
        // 是因为PageHelper内部定义了Page<Emp>继承了List
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    /**
     * @Transactional是spring提供的事务管理注解
     *可以加在方法,类,或者接口上 默认是运行时异常才会回滚
     * 因此需要通过rollbackFor来指定出现什么异常就回滚
     *
     */
    @Transactional(rollbackFor = {Exception.class})
    public void add(Emp emp) {
        try {
            //添加员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.add(emp);
            //添加员工工作经历
            /**
             * 我的思路:在service层使用循环,将list中的数据添加到数据库中
             */
       /* List<EmpExpr> list =  emp.getExprList();
        if (!list.isEmpty()){
            for (EmpExpr empExpr : list) {
                empExpr.setEmpId(emp.getId());
                empMapper.addExpr(empExpr);
            }
        }*/
            /**
             * 课程思路:在mapper.xml中使用foreach标签
             */

           /* int i  =1/0;*/
            List<EmpExpr> list = emp.getExprList();
            if (!list.isEmpty()) {
                for (EmpExpr empExpr : list) {
                    empExpr.setEmpId(emp.getId());
                }
                empExprMapper.addExpr(list);
            }
        } finally {
            empLogService.insertLog(new EmpLog(null, LocalDateTime.now(), "新增员工:"+emp));
        }

    }

}
