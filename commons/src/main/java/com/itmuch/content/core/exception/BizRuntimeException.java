package com.itmuch.content.core.exception;

import lombok.Data;

/**
 * AJax请求的异常类
 */
@Data
public class BizRuntimeException extends RuntimeException {
    private int code;
    private String error;

    public BizRuntimeException(int code, String error, String msg) {
        super(msg);
        this.code = code;
        this.error = error;
    }

    public BizRuntimeException(int code, String error, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.error = error;
    }
}
