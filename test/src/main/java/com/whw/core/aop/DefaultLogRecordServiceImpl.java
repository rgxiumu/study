package com.whw.core.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author wuhongwei
 * @version 1.0
 * @date 2021/9/30
 */
@Slf4j
public class DefaultLogRecordServiceImpl implements ILogRecordService {
    @Override
    public void record(String logRecord) {
        log.info("DefaultLogRecordServiceImpl");
    }
}
