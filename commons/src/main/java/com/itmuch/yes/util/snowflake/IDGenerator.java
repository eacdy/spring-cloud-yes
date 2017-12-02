package com.itmuch.yes.util.snowflake;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IDGenerator {
    public static synchronized long genId() {
        // 这里的synchronized不能少，否则会有并发问题。
        // 例如，当某个线程执行到long value = xxx时，另一个线程和该线程同时返回该value，此时就有问题了。
        long value = new DefaultKeyGenerator()
                .generateKey()
                .longValue();
        log.debug("生成的id = {}", value);
        return value;
    }
}
