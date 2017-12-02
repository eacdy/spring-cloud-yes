package com.itmuch.content.core.constants;

public interface ConstantsApp {
    /**
     * 页面找不到
     */
    String PAGE_NOT_FOUND = "/404";
    String TOKEN_HEADER = "Token";

    /**
     * 当前用户
     */
    String CURRENT_USER = "user";

    interface Role {
        String USER = "USER";
        String ADMIN = "ADMIN";
    }
}
