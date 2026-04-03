package com.itwu.Service.impl;

import com.itwu.Mapper.EmpLogMapper;
import com.itwu.pojo.EmpLog;
import com.itwu.Service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)//默认值为:REQUIRED 表示需要事务,若无事务则创建,否则加入事务
                                                                // REQUIRES_NEW 表示需要创建新事务,不管有无都创建
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
