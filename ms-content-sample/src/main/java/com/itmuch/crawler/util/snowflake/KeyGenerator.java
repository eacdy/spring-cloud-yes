package com.itmuch.crawler.util.snowflake;

/**
 * Key generator interface.
 *
 * @author zhangliang
 */
public interface KeyGenerator {

    /**
     * Generate key.
     *
     * @return generated key
     */
    Number generateKey();
}