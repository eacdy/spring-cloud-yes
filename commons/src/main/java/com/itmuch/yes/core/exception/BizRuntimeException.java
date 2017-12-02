package com.itmuch.yes.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务运行时异常类
 */
@EqualsAndHashCode(callSuper = true)
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
