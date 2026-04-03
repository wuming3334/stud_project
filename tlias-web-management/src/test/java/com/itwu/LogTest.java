package com.itwu;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog() {
        int i = 50144;
        int j = 65111;
        log.debug("开始计算...");
        int sum = i + j;
        log.debug("结束计算...");
        log.info("结果为:" + sum);
    }
}
