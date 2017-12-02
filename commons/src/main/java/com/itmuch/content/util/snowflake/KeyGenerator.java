package com.itmuch.content.util.snowflake;

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